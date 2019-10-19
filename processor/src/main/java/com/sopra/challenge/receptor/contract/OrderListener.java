package com.sopra.challenge.receptor.contract;

import com.sopra.challenge.receptor.configuration.MessageStream;
import com.sopra.challenge.receptor.model.Message;
import com.sopra.challenge.receptor.model.Order;
import com.sopra.challenge.receptor.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.concurrent.CompletableFuture.runAsync;

@Component
public class OrderListener {

  @Autowired
  private ProcessorService processorService;
  @Autowired
  private MessageStream messageStream;

  @StreamListener(MessageStream.INPUT)
  public void processorOrders(@Payload Message message) {
    //() -> runAsync(processorService.processorOrders(message.getOrders()));
    processorService.processorOrders(message.getOrders());
  }
}
