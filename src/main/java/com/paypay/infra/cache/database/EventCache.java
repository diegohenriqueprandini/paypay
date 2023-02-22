package com.paypay.infra.cache.database;

import com.paypay.domain.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "Event")
public class EventCache {

    @Id
    private UUID id;
    @Indexed
    private String name;
    @TimeToLive
    private Long timeout;

    public static EventCache from(Event.EventData data, Long timeout) {
        return new EventCache(
                data.id(),
                data.name(),
                timeout);
    }

    public Event.EventData toEventData() {
        return new Event.EventData(
                this.id,
                this.name
        );
    }
}
