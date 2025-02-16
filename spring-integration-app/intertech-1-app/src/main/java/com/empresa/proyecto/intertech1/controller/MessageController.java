package com.empresa.proyecto.intertech1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private PollableChannel pollableChannel;

    @Autowired
    private MessageChannel subscribableChannel;

    @PostMapping("/pollable")
    public String sendToPollableChannel(@RequestBody String message) {
        pollableChannel.send(MessageBuilder.withPayload(message).build());
        return "Message sent to PollableChannel.";
    }

    @GetMapping("/pollable")
    public String receiveFromPollableChannel() {
        Message<?> message = pollableChannel.receive();
        if (message != null) {
            return "PollableChannel received: " + message.getPayload();
        }
        return "No messages in PollableChannel.";
    }

    @PostMapping("/subscribable")
    public String sendToSubscribableChannel(@RequestBody String requestMessage) {
        Message<String> message = MessageBuilder.withPayload(requestMessage)
                .setHeader("correlationId", "12345")
                .build();
        subscribableChannel.send(message);
        return "Message sent to SubscribableChannel.";
    }
}
