package com.paypay.application.domain.entity;

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

    public Event(Database database) {
        this.id = database.id();
        this.name = database.name();
    }

    public record Database(UUID id, String name) {
    }
}

