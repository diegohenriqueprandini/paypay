package com.paypay.infra.repository.memory;

import com.paypay.domain.entity.Event;
import com.paypay.domain.repository.EventNotFountException;
import com.paypay.infra.cache.EventCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "application.config.repository", havingValue = "cached-memory")
public class EventCachedMemoryRepository extends EventMemoryRepository {

    private final EventCacheRepository eventCacheRepository;

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
