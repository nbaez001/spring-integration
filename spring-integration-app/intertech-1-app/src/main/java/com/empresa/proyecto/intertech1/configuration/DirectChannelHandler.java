package com.empresa.proyecto.intertech1.configuration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DirectChannelHandler {
    @ServiceActivator(inputChannel = "subscribableChannel")
    public void handleSubscribableMessage(Message<String> message) {
        System.out.println("SubscribableChannel received: " + message.getPayload());
    }
}
