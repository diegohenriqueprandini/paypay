package com.paypay.infra.repository.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class EventTable {

    @Id
    private final UUID id;

    private final String name;
}
