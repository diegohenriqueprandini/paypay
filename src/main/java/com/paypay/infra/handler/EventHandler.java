package com.paypay.infra.handler;

import com.paypay.application.domain.service.EventAlreadyExistsException;
import com.paypay.application.domain.service.EventNotFountException;
import com.paypay.application.domain.service.EventNotSavedException;
import com.paypay.application.domain.service.EventService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.UUID;

//@Component
@RequiredArgsConstructor
public class EventHandler {

    private final EventService eventService;

    public ServerResponse getAll(ServerRequest request) {
        return ServerResponse.ok()
                .body(eventService.getAll());
    }

    public ServerResponse getOne(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        try {
            return ServerResponse.ok()
                    .body(eventService.getOne(id));
        } catch (EventNotFountException e) {
            return ServerResponse.notFound().build();
        }
    }

    public ServerResponse createEvent(ServerRequest request) {
        try {
            EventBody body = request.body(EventBody.class);
            EventService.EventInput input = new EventService.EventInput(body.name());
            EventService.EventOutput output = eventService.createEvent(input);
            return ServerResponse
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse(
                            output.id(),
                            output.name()
                    ));
        } catch (EventAlreadyExistsException | ServletException | IOException e) {
            return ServerResponse.badRequest().build();
        } catch (EventNotSavedException e) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ServerResponse updateEvent(ServerRequest request) {
        try {
            UUID id = UUID.fromString(request.pathVariable("id"));
            EventBody body = request.body(EventBody.class);
            EventService.EventInput input = new EventService.EventInput(body.name);
            EventService.EventOutput output = eventService.updateEvent(id, input);
            return ServerResponse
                    .ok()
                    .body(new EventResponse(
                            output.id(),
                            output.name()
                    ));
        } catch (EventNotFountException e) {
            return ServerResponse.notFound().build();
        } catch (EventNotSavedException e) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (EventAlreadyExistsException | ServletException | IOException e) {
            return ServerResponse.badRequest().build();
        }
    }

    public ServerResponse removeEvent(ServerRequest request) {
        try {
            UUID id = UUID.fromString(request.pathVariable("id"));
            eventService.removeEvent(id);
            return ServerResponse.ok().build();
        } catch (EventNotFountException e) {
            return ServerResponse.notFound().build();
        }
    }

    private record EventBody(String name) {
    }

    private record EventResponse(UUID id, String name) {
    }
}
