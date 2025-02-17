package com.empresa.proyecto.orderprocessing.config;

import com.empresa.proyecto.orderprocessing.model.Order;
import com.empresa.proyecto.orderprocessing.model.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    private final Logger log = LoggerFactory.getLogger(IntegrationConfig.class);

    @Bean
    public MessageChannel ordersChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel orderItemsChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel processedItemsChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel deliveriesChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel notificationChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel electronicsChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel clothingChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel groceriesChannel() {
        return new DirectChannel();
    }

    @Splitter(inputChannel = "ordersChannel", outputChannel = "orderItemsChannel")
    public List<Message<OrderItem>> splitOrder(Message<Order> orderMessage) {
        Order order = orderMessage.getPayload();
        log.info("Splitting order with ID: {} containing {} items", order.getOrderId(), order.getItems().size());

        return order.getItems().stream()
                .peek(item -> log.info("Processing splitter: {}", item))
                .map(item -> MessageBuilder.withPayload(item)
                        .setHeader("orderId", order.getOrderId())  // Attach order ID in headers
                        .setHeader("totalItems", order.getItems().size())  // Attach total count
                        .build())
                .collect(Collectors.toList());
    }

    @Router(inputChannel = "orderItemsChannel")
    public String routeOrderItem(Message<OrderItem> itemMessage) {
        OrderItem item = itemMessage.getPayload();
        log.info("routing order item with ID: {}", item.getItemId());
        return switch (item.getItemType()) {
            case "ELECTRONICS" -> "electronicsChannel";
            case "CLOTHING" -> "clothingChannel";
            case "GROCERIES" -> "groceriesChannel";
            default -> throw new IllegalArgumentException("Unknown item type: " + item.getItemType());
        };
    }

    @ServiceActivator(inputChannel = "electronicsChannel", outputChannel = "processedItemsChannel")
    public Message<OrderItem> processElectronicsOrder(Message<OrderItem> itemMessage) {
        log.info("Processing electronics item: " + itemMessage.getPayload().getItemId());
        return itemMessage;
    }

    @ServiceActivator(inputChannel = "clothingChannel", outputChannel = "processedItemsChannel")
    public Message<OrderItem> processClothingOrder(Message<OrderItem> itemMessage) {
        log.info("Processing clothing item: " + itemMessage.getPayload().getItemId());
        return itemMessage;
    }

    @ServiceActivator(inputChannel = "groceriesChannel", outputChannel = "processedItemsChannel")
    public Message<OrderItem> processGroceriesOrder(Message<OrderItem> itemMessage) {
        log.info("Processing groceries item: " + itemMessage.getPayload().getItemId());
        return itemMessage;
    }

    @Aggregator(inputChannel = "processedItemsChannel", outputChannel = "deliveriesChannel")
    public Order aggregateOrderItems(List<Message<OrderItem>> messages) {
        log.info("Aggregating order items: " + messages.toString());

        if (messages.isEmpty()) {
            log.warn("No messages found for aggregation.");
            return null;
        }

        String orderId = messages.get(0).getHeaders().get("orderId", String.class);

        List<OrderItem> items = messages.stream()
                .map(Message::getPayload)
                .collect(Collectors.toList());

        return new Order(orderId, items);
    }

    @Bean
    @Transformer(inputChannel = "deliveriesChannel", outputChannel = "notificationChannel")
    public ObjectToJsonTransformer transformTojson() {
        return new ObjectToJsonTransformer(getMapper());
    }

    @Bean
    public Jackson2JsonObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonObjectMapper(mapper);
    }

}
