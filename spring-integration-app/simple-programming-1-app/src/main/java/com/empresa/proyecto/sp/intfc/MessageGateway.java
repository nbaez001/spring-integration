package com.empresa.proyecto.sp.intfc;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = "inputChannel")
    <S> void sendMessage(S request);
}
