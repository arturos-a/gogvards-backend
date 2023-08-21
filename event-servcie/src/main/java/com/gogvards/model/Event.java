package com.gogvards.model;

import io.micronaut.data.annotation.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(GeneratedValue.Type.UUID)
    private UUID id = UUID.randomUUID();
    private String userEventUuid;
    private String senderUserUuid;
    private String className;
    private String description;
    private LocalDateTime eventDate = LocalDateTime.now();

    public Event(String userEventUuid, String className, String description) {
        this.userEventUuid = userEventUuid;
        this.className = className;
        this.description = description;
    }
}
