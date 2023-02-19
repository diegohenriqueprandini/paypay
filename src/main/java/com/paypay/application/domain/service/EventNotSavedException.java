package com.paypay.application.domain.service;

import java.util.UUID;

public class EventNotSavedException extends Exception {
    public EventNotSavedException(UUID id, String name) {
        super(String.format("Event not saved: %s %s", id, name));
    }
}
