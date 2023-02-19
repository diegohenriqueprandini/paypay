package com.paypay.infra.repository.memory;

import com.paypay.application.domain.repository.EventRepository;
import com.paypay.application.domain.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventMemoryRepository implements EventRepository {

    private final List<Event> events = new ArrayList<>();

    @Override
    public List<Event> getAll() {
        return List.copyOf(events);
    }

    @Override
    public Optional<Event> getOne(UUID id) {
        return events.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public void remove(Event event) {
        events.remove(event);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return events.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }
}
