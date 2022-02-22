package es.jaiem.javaddd.domain.exceptions;


public class AlreadyOwner extends DomainException {
    public AlreadyOwner(String message) {
        super(message);
    }
}
