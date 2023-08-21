package com.gogvards.service.impl;


import com.gogvards.dto.ClassEventMessageDto;
import com.gogvards.dto.EventDto;
import com.gogvards.dto.EventMessageDto;
import com.gogvards.messaging.EventClient;
import com.gogvards.repository.EventRepository;
import com.gogvards.service.EventService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final EventClient eventClient;

    @Override
    public Flux<EventDto> getEvent(String userUuid) {
        return eventRepository.findByUserEventUuidOrderByEventDate(userUuid)
                .map(i -> modelMapper.map(i, EventDto.class))
                .switchIfEmpty(Mono.error(new HttpStatusException(HttpStatus.UNAUTHORIZED, "")));
    }

    @Override
    public void sendEvent(EventMessageDto eventMessageDto) {
        eventMessageDto.getUsers().stream().forEach(item -> {
            eventClient.send(item, eventMessageDto.getMessage().getBytes());
        });
    }

    @Override
    public void sendEvent(ClassEventMessageDto eventMessageDto) {

    }
}
