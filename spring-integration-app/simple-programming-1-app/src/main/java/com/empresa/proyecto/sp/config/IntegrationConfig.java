package com.empresa.proyecto.sp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.empresa.proyecto.sp")
public class IntegrationConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.getStandaloneConfiguration().setHostName("localhost");
        factory.getStandaloneConfiguration().setPort(6379);
        return factory;
    }

    @Bean
    public DirectChannel receiverChannel() {
        return new DirectChannel();
    }

    @Bean
    public RedisQueueMessageDrivenEndpoint consumerEndPoint() {
        RedisQueueMessageDrivenEndpoint endpoint = new RedisQueueMessageDrivenEndpoint("Redis-Queue",
                jedisConnectionFactory());
        endpoint.setOutputChannelName("receiverChannel");
        return endpoint;
    }
}
