package com.gogvards.repository;

import com.gogvards.model.Event;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
@Repository
public interface EventRepository extends ReactorCrudRepository<Event, UUID> {
    Flux<Event> findByUserEventUuidOrderByEventDate(String uuid);
}
