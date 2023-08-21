package com.gogvards.messaging;

import com.gogvards.model.Event;
import com.gogvards.repository.EventRepository;
import io.micronaut.messaging.annotation.MessageHeader;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RabbitClient
@Slf4j
public class EventListener {
    @Inject
    private EventRepository eventRepository;

    @Queue("events")
    public Mono<Void> receive(@MessageHeader("userId") String userId, byte[] data) {
        eventRepository.save(new Event(userId, null, new String(data)))
                .subscribe(result -> log.info("Entity has been saved: {}", result));
        return Mono.empty();
    }
}
