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

    public Event(EventData eventData) {
        this.id = eventData.id();
        this.name = eventData.name();
    }

    public record EventData(UUID id, String name) {
    }
}

