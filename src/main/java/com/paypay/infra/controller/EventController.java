package com.paypay.infra.controller;

import com.paypay.application.EventService;
import com.paypay.domain.repository.EventAlreadyExistsException;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventService.EventOutput>> getAll() {
        List<EventService.EventOutput> output = eventService.getAll();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventService.EventOutput> getOne(@PathVariable UUID id) throws EventNotFountException {
        EventService.EventOutput output = eventService.getOne(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<EventService.EventOutput> createEvent(@RequestBody EventService.EventInput input) throws EventNotFountException, EventAlreadyExistsException {
        EventService.EventOutput output = eventService.createEvent(input);
        return ResponseEntity.created(ControllerUtils.createUri(output.id(), "/events")).body(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventService.EventOutput> updateEvent(@PathVariable UUID id, @RequestBody EventService.EventInput input) throws EventNotFountException, EventAlreadyExistsException {
        EventService.EventOutput output = eventService.updateEvent(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEvent(@PathVariable UUID id) throws EventNotFountException {
        eventService.removeEvent(id);
        return ResponseEntity.ok().build();
    }
}
