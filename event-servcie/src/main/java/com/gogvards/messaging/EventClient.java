package com.gogvards.messaging;

import io.micronaut.messaging.annotation.MessageHeader;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.micronaut.rabbitmq.annotation.RabbitProperty;

@RabbitClient("eventExchange")
public interface EventClient {
    @Binding("event")
    void send(@MessageHeader("userId") String user, byte[] data);
}
