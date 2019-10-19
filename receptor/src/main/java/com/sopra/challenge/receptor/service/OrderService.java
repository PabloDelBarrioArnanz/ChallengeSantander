package com.sopra.challenge.receptor.service;

import com.sopra.challenge.receptor.configuration.MessageStream;
import com.sopra.challenge.receptor.model.Message;
import com.sopra.challenge.receptor.model.Order;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

public interface OrderService {

    void sendOrders(List<Order> orders);

    default void send(MessageStream messageStream, Message message) {
        MessageChannel messageChannel = messageStream.outboundOrders();
        messageChannel.send(MessageBuilder
                .withPayload(message)
                .build());
    }

}
