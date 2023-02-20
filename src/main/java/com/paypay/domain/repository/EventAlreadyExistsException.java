package com.paypay.domain.repository;

public class EventAlreadyExistsException extends Exception {
    public EventAlreadyExistsException(String name) {
        super("Event already exists: " + name);
    }
}
