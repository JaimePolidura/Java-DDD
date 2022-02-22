package es.jaiem.javaddd.domain.async;

import es.jaiem.javaddd.domain.event.DomainEvent;

import java.util.Map;

public interface AsyncDomainEvent extends Message{
    DomainEvent fromPrimitives(Map<String, Object> primitives);
    String eventName();
}
