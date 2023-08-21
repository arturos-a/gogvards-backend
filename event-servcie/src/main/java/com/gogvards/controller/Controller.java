package com.gogvards.controller;

import com.gogvards.dto.ClassEventMessageDto;
import com.gogvards.dto.EventDto;
import com.gogvards.dto.EventMessageDto;
import com.gogvards.service.EventService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@io.micronaut.http.annotation.Controller("/api")
@Slf4j
public class Controller {
    @Inject
    private EventService eventService;

    @Value("${service.auth-header}")
    public String authHeaderName;

    @Get("/event")
    public Flux<EventDto> getEvents(HttpHeaders headers) {
        log.info("header Name:" + authHeaderName);
        return eventService.getEvent(headers.get(authHeaderName));
    }

    @Post("/event")
    public void sendMessage(@Body EventMessageDto eventMessageDto) {
        eventService.sendEvent(eventMessageDto);
    }

    @Post("/classEvent")
    public void sendMessageToClass(@Body ClassEventMessageDto eventMessageDto) {
        eventService.sendEvent(eventMessageDto);
    }
}
