package com.paypay.infra.repository.database;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Profile("production")
@RequiredArgsConstructor
public class EventDatabaseRepository implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    @Override
    public List<Event> getAll() {
        List<EventTable> databaseData = eventJpaRepository.findAll();
        return databaseData.stream()
                .map(data -> new Event(data.toEventData()))
                .collect(Collectors.toList());
    }

    @Override
    public Event getOne(UUID id) throws EventNotFountException {
        return eventJpaRepository.findById(id)
                .map(data -> new Event(data.toEventData()))
                .orElseThrow(() -> new EventNotFountException(id));
    }

    @Override
    public void save(Event event) {
        eventJpaRepository.save(new EventTable(
                event.getId(),
                event.getName()
        ));
    }

    @Override
    public void remove(Event event) {
        eventJpaRepository.delete(new EventTable(
                event.getId(),
                event.getName()
        ));
    }

    @Override
    public Optional<Event> findByName(String name) {
        return eventJpaRepository.findByName(name)
                .map(data -> new Event(data.toEventData()));
    }
}
