package com.paypay.infra.controller;

import com.paypay.domain.service.EventAlreadyExistsException;
import com.paypay.domain.service.EventNotFountException;
import com.paypay.domain.service.EventNotSavedException;
import com.paypay.domain.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {
        List<EventService.EventOutput> output = eventService.getAll();
        return ResponseEntity.ok(output.stream()
                .map(item -> new EventResponse(
                        item.id(),
                        item.name()
                ))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getOne(@PathVariable UUID id) {
        try {
            EventService.EventOutput output = eventService.getOne(id);
            return ResponseEntity.ok(new EventResponse(
                    output.id(),
                    output.name()
            ));
        } catch (EventNotFountException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventBody body) {
        try {
            EventService.EventInput input = new EventService.EventInput(body.name());
            EventService.EventOutput output = eventService.createEvent(input);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse(
                            output.id(),
                            output.name()
                    ));
        } catch (EventAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        } catch (EventNotSavedException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable UUID id, @RequestBody EventBody body) {
        try {
            EventService.EventInput input = new EventService.EventInput(body.name);
            EventService.EventOutput output = eventService.updateEvent(id, input);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse(
                            output.id(),
                            output.name()
                    ));
        } catch (EventNotFountException e) {
            return ResponseEntity.notFound().build();
        } catch (EventNotSavedException e) {
            return ResponseEntity.internalServerError().build();
        } catch (EventAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEvent(@PathVariable UUID id) {
        try {
            eventService.removeEvent(id);
            return ResponseEntity.ok().build();
        } catch (EventNotFountException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private record EventBody(String name) {
    }

    private record EventResponse(UUID id, String name) {
    }
}
