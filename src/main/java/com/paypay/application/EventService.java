package com.paypay.application;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventAlreadyExistsException;
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

    public EventOutput getOne(UUID id) {
        Event event = eventRepository.getOne(id);
        return new EventOutput(
                event.getId(),
                event.getName()
        );
    }

    public List<EventOutput> getAll() {
        return eventRepository.getAll().stream()
                .map(item -> new EventOutput(
                        item.getId(),
                        item.getName()
                ))
                .collect(Collectors.toList());
    }

    public EventOutput createEvent(EventInput input) throws EventAlreadyExistsException {
        Optional<Event> existing = eventRepository.findByName(input.name);
        if (existing.isPresent())
            throw new EventAlreadyExistsException(input.name);
        UUID id = UUID.randomUUID();
        Event toSave = new Event(id, input.name());
        eventRepository.save(toSave);
        Event saved = eventRepository.getOne(id);
        return new EventOutput(
                saved.getId(),
                saved.getName()
        );
    }

    public EventOutput updateEvent(UUID id, EventInput input) {
        Event existingById = eventRepository.getOne(id);
        Optional<Event> existingByName = eventRepository.findByName(input.name);
        if (existingByName.isPresent() && !existingByName.get().getId().equals(id))
            throw new EventAlreadyExistsException(input.name);
        existingById.setName(input.name);
        eventRepository.save(existingById);
        Event saved = eventRepository.getOne(id);
        return new EventOutput(
                saved.getId(),
                saved.getName()
        );
    }

    public void removeEvent(UUID id) {
        Event existing = eventRepository.getOne(id);
        eventRepository.remove(existing);
    }

    public record EventInput(String name) {
    }

    public record EventOutput(UUID id, String name) {
    }
}
