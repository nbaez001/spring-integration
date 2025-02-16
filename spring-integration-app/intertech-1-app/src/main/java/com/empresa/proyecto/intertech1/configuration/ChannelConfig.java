package com.empresa.proyecto.intertech1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
public class ChannelConfig {

    @Bean
    public PollableChannel pollableChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel subscribableChannel() {
        return new DirectChannel();
    }
}