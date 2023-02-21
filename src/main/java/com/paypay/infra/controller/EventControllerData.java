package com.paypay.infra.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventControllerData {

    private LocalDateTime timestamp;
    private String message;
    private String detail;
}
