package com.paypay.domain.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Event {

    private final UUID id;
    private String name;

    public Event(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Event from(EventData data) {
        return new Event(
                data.id(),
                data.name()
        );
    }

    public EventData toData() {
        return new EventData(
                this.id,
                this.name
        );
    }

    public record EventData(UUID id, String name) {
    }
}

