package com.paypay.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventTable, UUID> {

    Optional<EventTable> findByName(String name);
}
