package com.sopra.challenge.receptor.communicator;

import com.sopra.challenge.receptor.configuration.MessageStream;
import com.sopra.challenge.receptor.model.Message;
import com.sopra.challenge.receptor.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageSender {

  private MessageChannel messageChannelProcessed;
  private MessageChannel messageChannelSaved;

  @Autowired
  public MessageSender(MessageStream messageStream) {
    messageChannelProcessed = messageStream.outboundProcessed();
    messageChannelSaved = messageStream.outboundProcessed();
  }

  public void sendMessageProcessed(List<Order> orders) {
    processesMessage(messageChannelProcessed, orders);
  }

  public void sendMessageSaved(List<Order> orders) {
    processesMessage(messageChannelSaved, orders);
  }

  private void processesMessage(MessageChannel channel, List<Order> orders) {
    channel
      .send(MessageBuilder
        .withPayload(orders)
        .build());
  }
}
