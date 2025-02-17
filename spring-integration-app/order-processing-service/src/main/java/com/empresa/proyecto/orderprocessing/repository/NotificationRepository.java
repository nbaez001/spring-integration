package com.empresa.proyecto.orderprocessing.repository;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {

    private final MessageChannel notificationChannel;

    public NotificationRepository(MessageChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public void sendNotification(String message) {
        Message<String> msg = MessageBuilder.withPayload(message)
                .setHeader("kafka_topic", "notifications-topic") // Optional, if topic is dynamic
                .build();

        notificationChannel.send(msg);
    }
}
