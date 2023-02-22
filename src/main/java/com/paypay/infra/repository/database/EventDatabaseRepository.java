package com.paypay.infra.repository.database;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.domain.repository.EventRepository;
import com.paypay.infra.jpa.EventJpaRepository;
import com.paypay.infra.jpa.EventTable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "application.config.repository", havingValue = "database")
public class EventDatabaseRepository implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    @Override
    public List<Event> getAll() {
        List<EventTable> databaseData = eventJpaRepository.findAll();
        return databaseData.stream()
                .map(data -> Event.from(data.toEventData()))
                .collect(Collectors.toList());
    }

    @Override
    public Event getOne(UUID id) throws EventNotFountException {
        return eventJpaRepository.findById(id)
                .map(data -> Event.from(data.toEventData()))
                .orElseThrow(() -> new EventNotFountException(id));
    }

    @Override
    public void save(Event event) {
        eventJpaRepository.save(EventTable.from(event.toData()));
    }

    @Override
    public void remove(Event event) {
        eventJpaRepository.delete(EventTable.from(event.toData()));
    }

    @Override
    public Optional<Event> findByName(String name) {
        return eventJpaRepository.findByName(name)
                .map(data -> Event.from(data.toEventData()));
    }
}
