package es.jaime.javaddd.domain.async;

import es.jaime.javaddd.domain.event.DomainEvent;

import java.util.Map;

public interface AsyncDomainEvent extends Message{
    DomainEvent fromPrimitives(Map<String, Object> primitives);
    String eventName();
}
