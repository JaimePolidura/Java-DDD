package es.jaime.javaddd.domain.exceptions;


public class AlreadyExists extends DomainException {
    public AlreadyExists(String message) {
        super(message);
    }
}
