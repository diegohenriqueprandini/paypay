package com.paypay.infra.repository.database;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.infra.cache.EventCacheRepository;
import com.paypay.infra.jpa.EventJpaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@ConditionalOnProperty(value = "application.config.repository", havingValue = "cached-database")
public class EventCachedDatabaseRepository extends EventDatabaseRepository {

    private final EventCacheRepository eventCacheRepository;

    public EventCachedDatabaseRepository(EventJpaRepository eventJpaRepository, EventCacheRepository eventCacheRepository) {
        super(eventJpaRepository);
        this.eventCacheRepository = eventCacheRepository;
    }

    @Override
    public List<Event> getAll() {
        return super.getAll();
    }

    @Override
    public Event getOne(UUID id) throws EventNotFountException {
        return super.getOne(id);
    }

    @Override
    public void save(Event event) {
        super.save(event);
    }

    @Override
    public void remove(Event event) {
        super.remove(event);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return super.findByName(name);
    }
}
