package com.paypay.infra.cache.memory;

import com.paypay.domain.entity.Event;
import com.paypay.infra.cache.EventCacheRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value = "application.config.cache", havingValue = "memory")
public class EventCacheMemoryRepository implements EventCacheRepository {

    private final List<Event.EventData> events = new ArrayList<>();

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
    public Optional<Event> get(UUID id) {
        return events.stream()
                .filter(item -> item.id().equals(id))
                .map(Event::from)
                .findFirst();
    }

    @Override
    public List<Event> getAll() {
        return events.stream()
                .map(Event::from)
                .collect(Collectors.toList());
    }
}
