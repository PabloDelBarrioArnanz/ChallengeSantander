package com.sopra.challenge.receptor.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private Map<String, List<Order>> orders = new HashMap<>();;

    public Map<String, List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, List<Order>> orders) {
        this.orders = orders;
    }

    public static class MessageBuilder {

        private static final String TOPIC = "orders";
        private Map<String, List<Order>> orders = new HashMap<>();;

        public MessageBuilder setOrders(Map<String, List<Order>> orders) {
            this.orders = orders;
            return this;
        }

        public Message build(){
            Message message = new Message();
            message.orders = orders;
            return message;
        }

    }

}
