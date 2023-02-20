package com.paypay.domain.service;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventOutput getOne(UUID id) throws EventNotFountException {
        return eventRepository.getOne(id)
                .map(item -> new EventOutput(
                        item.getId(),
                        item.getName()
                ))
                .orElseThrow(() -> new EventNotFountException(id));
    }

    public List<EventOutput> getAll() {
        return eventRepository.getAll().stream()
                .map(item -> new EventOutput(
                        item.getId(),
                        item.getName()
                ))
                .collect(Collectors.toList());
    }

    public EventOutput createEvent(EventInput input) throws EventAlreadyExistsException, EventNotSavedException {
        Optional<Event> existing = eventRepository.findByName(input.name);
        if (existing.isPresent())
            throw new EventAlreadyExistsException(input.name);
        UUID id = UUID.randomUUID();
        Event event = new Event(id, input.name());
        eventRepository.save(event);
        return eventRepository.getOne(id)
                .map(item -> new EventOutput(
                        item.getId(),
                        item.getName()
                ))
                .orElseThrow(() -> new EventNotSavedException(id, input.name));
    }

    public EventOutput updateEvent(UUID id, EventInput input) throws EventNotFountException, EventAlreadyExistsException, EventNotSavedException {
        Event existing = eventRepository.getOne(id)
                .orElseThrow(() -> new EventNotFountException(id));

        Optional<Event> existingByName = eventRepository.findByName(input.name);
        if (existingByName.isPresent() && !existingByName.get().getId().equals(id))
            throw new EventAlreadyExistsException(input.name);

        existing.setName(input.name);
        eventRepository.save(existing);
        return eventRepository.getOne(id)
                .map(item -> new EventOutput(
                        item.getId(),
                        item.getName()
                ))
                .orElseThrow(() -> new EventNotSavedException(id, input.name));
    }

    public void removeEvent(UUID id) throws EventNotFountException {
        Event existing = eventRepository.getOne(id)
                .orElseThrow(() -> new EventNotFountException(id));
        eventRepository.remove(existing);
    }

    public record EventInput(String name) {
    }

    public record EventOutput(UUID id, String name) {
    }
}
