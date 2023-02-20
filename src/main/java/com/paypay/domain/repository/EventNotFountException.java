package com.paypay.domain.repository;

import java.util.UUID;

public class EventNotFountException extends RuntimeException {
    public EventNotFountException(UUID id) {
        super("Event not found: " + id);
    }
}
