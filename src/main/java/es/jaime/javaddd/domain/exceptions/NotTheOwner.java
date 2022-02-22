package es.jaime.javaddd.domain.exceptions;

public class NotTheOwner extends DomainException {
    public NotTheOwner(String message) {
        super(message);
    }
}
