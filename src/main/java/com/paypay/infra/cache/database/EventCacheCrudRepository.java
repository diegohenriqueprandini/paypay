package com.paypay.infra.cache.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface EventCacheCrudRepository extends CrudRepository<EventCache, UUID> {

    List<EventCache> findByName(String name);

    @Override
    List<EventCache> findAll();
}
