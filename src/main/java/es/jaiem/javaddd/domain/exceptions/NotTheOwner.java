package es.jaiem.javaddd.domain.exceptions;

import es.jaime.gateway._shared.domain.DomainException;

public class NotTheOwner extends DomainException {
    public NotTheOwner(String message) {
        super(message);
    }
}
