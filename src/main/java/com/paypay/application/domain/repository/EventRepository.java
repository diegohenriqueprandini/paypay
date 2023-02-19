package com.paypay.application.domain.repository;

import com.paypay.application.domain.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

    List<Event> getAll();

    Optional<Event> getOne(UUID id);

    void save(Event event);

    void remove(Event event);

    Optional<Event> findByName(String name);
}
