package es.jaime.javaddd.domain.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Object... other) {
        super(String.format(message, other));
    }
}
