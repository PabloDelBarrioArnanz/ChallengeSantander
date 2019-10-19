package com.sopra.challenge.receptor.service.imp;

import com.sopra.challenge.receptor.configuration.MessageStream;
import com.sopra.challenge.receptor.model.Message;
import com.sopra.challenge.receptor.model.Order;
import com.sopra.challenge.receptor.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class OrdersServiceImp implements OrderService {

  private Logger Logger = LoggerFactory.getLogger(OrdersServiceImp.class);
  private MessageStream messageStream;

  @Autowired
  public OrdersServiceImp(MessageStream messageStream) {
    this.messageStream = messageStream;
  }

  public void sendOrders(List<Order> orders) {
    send(messageStream,
      buildMessage(orders.stream()
        .collect(groupingBy(Order::getCity))));
  }

  public Message buildMessage(Map<String, List<Order>> orders) {
    return new Message.MessageBuilder()
      .setOrders(orders)
      .build();
  }

  @StreamListener(MessageStream.INPUT_PROCESSED)
  public void receiveFeedBackProcessed(@Payload Order order) {
    Logger.info("Processed order -> " + order);
  }

  @StreamListener(MessageStream.INPUT_SAVED)
  public void receiveFeedSaved(@Payload List<Order> orders) {
    Logger.info("Saved order -> " + orders);
  }

  @StreamListener(MessageStream.INPUT_PROCESSED)
  public void receiveFeedBack(@Payload Order order) {
    Logger.info("Processed order -> " + order);
  }

}
