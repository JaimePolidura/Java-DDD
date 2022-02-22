package es.jaime.javaddd.domain.event;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);

    void publish(DomainEvent event);
}
