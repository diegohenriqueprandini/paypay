package com.paypay.application.domain.service;

import java.util.UUID;

public class EventNotFountException extends Exception {
    public EventNotFountException(UUID id) {
        super("Event not found: " + id);
    }
}
