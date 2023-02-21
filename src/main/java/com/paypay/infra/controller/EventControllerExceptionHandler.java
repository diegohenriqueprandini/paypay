package com.paypay.infra.controller;

import com.paypay.domain.repository.EventAlreadyExistsException;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.utils.JsonUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EventControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var details = createDefaultExceptionDetails(e, request);
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(details);
    }

    @ExceptionHandler(EventNotFountException.class)
    public final ResponseEntity<EventControllerData> eventNotFountException(EventNotFountException e, WebRequest request) {
        final var details = createDefaultExceptionDetails(e, request);
        return createDefaultResponseByException(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public final ResponseEntity<EventControllerData> eventAlreadyExistsException(EventAlreadyExistsException e, WebRequest request) {
        final var details = createDefaultExceptionDetails(e, request);
        return createDefaultResponseByException(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonUtils.JsonUtilsException.class)
    public final ResponseEntity<EventControllerData> jsonUtilsException(JsonUtils.JsonUtilsException e, WebRequest request) {
        final var details = createDefaultExceptionDetails(e, request);
        return createDefaultResponseByException(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private EventControllerData createDefaultExceptionDetails(Exception e, WebRequest request) {
        return EventControllerData.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .detail(createDetail(request))
                .build();
    }

    private String createDetail(WebRequest request) {
        String httpMethod = "method=" + ((ServletWebRequest) request).getHttpMethod();
        String uri = request.getDescription(false);
        return httpMethod + "," + uri;
    }

    private ResponseEntity<EventControllerData> createDefaultResponseByException(EventControllerData details, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(details);
    }
}
