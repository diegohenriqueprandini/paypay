package com.paypay.infra.repository.database;

import com.paypay.application.domain.repository.EventRepository;
import com.paypay.application.domain.entity.Event;
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
                .map((data) -> new Event(new Event.Database(
                        data.getId(),
                        data.getName()
                )))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> getOne(UUID id) {
        return eventJpaRepository.findById(id)
                .map((EventTable data) -> new Event(new Event.Database(
                        data.getId(),
                        data.getName()
                )));
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
                .map((EventTable data) -> new Event(new Event.Database(
                        data.getId(),
                        data.getName()
                )));
    }
}
