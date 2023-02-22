package com.paypay.infra.repository.memory;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.domain.repository.EventRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value = "application.config.repository", havingValue = "memory")
public class EventMemoryRepository implements EventRepository {

    private final List<Event.EventData> events = new ArrayList<>();

    @Override
    public List<Event> getAll() {
        return events.stream()
                .map(Event::from)
                .collect(Collectors.toList());
    }

    @Override
    public Event getOne(UUID id) throws EventNotFountException {
        return events.stream()
                .filter(item -> item.id().equals(id))
                .map(Event::from)
                .findFirst()
                .orElseThrow(() -> new EventNotFountException(id));
    }

    @Override
    public void save(Event event) {
        Event.EventData eventData = event.toData();
        Optional<Event.EventData> existing = events.stream()
                .filter(item -> item.id().equals(event.getId()))
                .findFirst();
        if (existing.isPresent()) {
            events.remove(existing.get());
            events.add(eventData);
            return;
        }
        events.add(eventData);
    }

    @Override
    public void remove(Event event) {
        Optional<Event.EventData> existing = events.stream()
                .filter(item -> item.id().equals(event.getId()))
                .findFirst();
        existing.ifPresent(events::remove);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return events.stream()
                .filter(item -> item.name().equals(name))
                .map(Event::from)
                .findFirst();
    }
}
