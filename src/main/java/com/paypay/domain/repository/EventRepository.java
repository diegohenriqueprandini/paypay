package com.paypay.domain.repository;

import com.paypay.domain.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

    List<Event> getAll();

    Event getOne(UUID id);

    void save(Event event);

    void remove(Event event);

    Optional<Event> findByName(String name);
}
