package com.paypay.infra.repository.memory;

import com.paypay.application.domain.entity.Event;
import com.paypay.application.domain.repository.EventRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Profile("memory")
public class EventMemoryRepository implements EventRepository {

    private final List<Event.Database> events = new ArrayList<>();

    @Override
    public List<Event> getAll() {
        return events.stream()
                .map(Event::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> getOne(UUID id) {
        return events.stream()
                .filter(item -> item.id().equals(id))
                .map(Event::new)
                .findFirst();
    }

    @Override
    public void save(Event event) {
        Event.Database databaseData = new Event.Database(
                event.getId(),
                event.getName()
        );
        Optional<Event.Database> existing = events.stream()
                .filter(item -> item.id().equals(event.getId()))
                .findFirst();

        if (existing.isPresent()) {
            events.remove(existing.get());
            events.add(databaseData);
            return;
        }

        events.add(databaseData);
    }

    @Override
    public void remove(Event event) {
        Optional<Event.Database> existing = events.stream()
                .filter(item -> item.id().equals(event.getId()))
                .findFirst();
        existing.ifPresent(events::remove);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return events.stream()
                .filter(item -> item.name().equals(name))
                .map(Event::new)
                .findFirst();
    }
}
