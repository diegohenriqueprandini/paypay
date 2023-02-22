package com.paypay.infra.cache;

import com.paypay.domain.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventCacheRepository {

    void save(Event event);

    Optional<Event> get(UUID id);

    List<Event> getAll();
}
