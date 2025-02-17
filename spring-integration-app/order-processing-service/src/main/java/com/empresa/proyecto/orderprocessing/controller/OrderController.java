package com.empresa.proyecto.orderprocessing.controller;

import com.empresa.proyecto.orderprocessing.gateway.OrderGateway;
import com.empresa.proyecto.orderprocessing.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderGateway orderGateway;

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        orderGateway.placeOrder(order);
        return "Order received successfully";
    }
}