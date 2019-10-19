package com.sopra.challenge.receptor.controller;


import com.sopra.challenge.receptor.model.Order;
import com.sopra.challenge.receptor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    private OrderService ordersService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.ordersService = orderService;
    }

    @PostMapping()
    public ResponseEntity<Void> postOrders(@RequestBody List<Order> orders) {
        ordersService.sendOrders(orders);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
