package es.jaime.javaddd.domain.event;

import java.util.List;

public interface EventBus {
    void publishAsync(final List<DomainEvent> events);

    void publishAsync(DomainEvent event);

    void publishSync(final List<DomainEvent> events);

    void publishSync(DomainEvent event);
}
