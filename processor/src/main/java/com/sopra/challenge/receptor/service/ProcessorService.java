package com.sopra.challenge.receptor.service;

import com.sopra.challenge.receptor.communicator.MessageSender;
import com.sopra.challenge.receptor.model.Order;
import com.sopra.challenge.receptor.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProcessorService {

  private MessageSender messageSender;
  private OrdersRepository ordersRepository;

  @Autowired
  public ProcessorService(OrdersRepository ordersRepository, MessageSender messageSender) {
    this.ordersRepository = ordersRepository;
    this.messageSender = messageSender;
  }

  public void processorOrders(Map<String, List<Order>> order) {

    List<List<Order>> list = order
      .entrySet()
      .stream()
      .map(this::mergeOrders)
      .collect(Collectors.toList());

    sendOrdersAndUpdateDB(list,
      isValidOrder(),
      validOrders -> messageSender.sendMessageProcessed(validOrders),
      validOrders -> ordersRepository.deleteAll(validOrders));

    sendOrdersAndUpdateDB(list,
      isValidOrder().negate(),
      invalidOrders -> messageSender.sendMessageSaved(invalidOrders),
      invalidOrders -> ordersRepository.saveAll(invalidOrders));
  }

  private List<Order> mergeOrders(Map.Entry<String, List<Order>> entry) {
    return Stream.concat(
      entry.getValue().stream(),
      ordersRepository.findByCity(entry.getKey()).stream()
    ).collect(Collectors.toList());
  }

  private Predicate<List<Order>> isValidOrder() {
    return orders -> orders.size() >= 10 ||
      orders.stream()
        .mapToDouble(Order::getKg)
        .sum() > 15;
  }

  private void sendOrdersAndUpdateDB(List<List<Order>> orders, Predicate<List<Order>> ordersFilter,
                                     Consumer<List<Order>> sendOperation, Consumer<List<Order>> dbOperation) {
    orders.stream()
      .parallel()
      .filter(ordersFilter)
      .peek(sendOperation)
      .forEach(dbOperation);
  }
}
