package com.paypay.infra.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;

//@Configuration
@RequiredArgsConstructor
public class EventRouter {

//    @Bean
    public RouterFunction<ServerResponse> route(EventHandler eventHandler) {
        return RouterFunctions
                .route(GET("/events"), eventHandler::getAll)
                .andRoute(GET("events/{id}"), eventHandler::getOne)
                .andRoute(POST("/events"), eventHandler::createEvent)
                .andRoute(PUT("/events/{id}"), eventHandler::updateEvent)
                .andRoute(DELETE("/events/{id}"), eventHandler::removeEvent);
    }
}
