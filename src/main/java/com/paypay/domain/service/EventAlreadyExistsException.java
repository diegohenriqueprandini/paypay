package com.paypay.domain.service;

public class EventAlreadyExistsException extends Exception {
    public EventAlreadyExistsException(String name) {
        super("Event already exists: " + name);
    }
}
