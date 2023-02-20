package com.paypay.domain.repository;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException(String name) {
        super("Event already exists: " + name);
    }
}
