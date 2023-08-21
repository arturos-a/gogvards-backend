package com.gogvards.service;

import com.gogvards.dto.ClassEventMessageDto;
import com.gogvards.dto.EventDto;
import com.gogvards.dto.EventMessageDto;
import com.gogvards.model.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EventService {
    Flux<EventDto> getEvent(String userUuid);

    void sendEvent(EventMessageDto eventMessageDto);

    void sendEvent(ClassEventMessageDto eventMessageDto);
}
