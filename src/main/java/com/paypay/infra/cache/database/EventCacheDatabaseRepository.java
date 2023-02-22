package com.paypay.infra.cache.database;

import com.paypay.domain.entity.Event;
import com.paypay.infra.cache.EventCacheRepository;
import com.paypay.infra.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({ApplicationProperties.Cache.class})
@ConditionalOnProperty(value = "application.config.cache", havingValue = "database")
public class EventCacheDatabaseRepository implements EventCacheRepository {

    private final EventCacheCrudRepository eventCacheCrudRepository;
    private final ApplicationProperties.Cache cacheProperties;

    @Override
    public void save(Event event) {
        EventCache cache = EventCache.from(event.toData(), cacheProperties.getDefaultTimeout());
        eventCacheCrudRepository.save(cache);
    }

    @Override
    public Optional<Event> get(UUID id) {
        if (id == null || id.toString().isBlank())
            return Optional.empty();
        return eventCacheCrudRepository.findById(id)
                .map(cache -> Event.from(cache.toEventData()));
    }

    @Override
    public List<Event> getAll() {
        return eventCacheCrudRepository.findAll().stream()
                .map(cache -> Event.from(cache.toEventData()))
                .collect(Collectors.toList());
    }
}
