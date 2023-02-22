package com.paypay.infra.jpa;

import com.paypay.domain.entity.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EventTable {

    @Id
    private UUID id;

    private String name;

    public static EventTable from(Event.EventData data) {
        return new EventTable(
                data.id(),
                data.name());
    }

    public Event.EventData toEventData() {
        return new Event.EventData(
                this.id,
                this.name
        );
    }
}
