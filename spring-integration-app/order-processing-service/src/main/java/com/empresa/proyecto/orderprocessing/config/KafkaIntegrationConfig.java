package com.empresa.proyecto.orderprocessing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class KafkaIntegrationConfig {

    private static final String KAFKA_TOPIC = "notification-topic";

    @Bean
    @ServiceActivator(inputChannel = "notificationChannel")
    public MessageHandler kafkaMessageHandler(KafkaTemplate<String, String> kafkaTemplate) {
        KafkaProducerMessageHandler<String, String> handler =
                new KafkaProducerMessageHandler<>(kafkaTemplate);
        handler.setTopicExpression(new LiteralExpression(KAFKA_TOPIC));
        return handler;
    }
}
