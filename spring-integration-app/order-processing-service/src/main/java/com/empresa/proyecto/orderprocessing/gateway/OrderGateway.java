package com.empresa.proyecto.orderprocessing.gateway;

import com.empresa.proyecto.orderprocessing.model.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface OrderGateway {

    @Gateway(requestChannel = "ordersChannel")
    void placeOrder(Order order);
}
