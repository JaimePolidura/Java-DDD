package es.jaime.javaddd.domain.exceptions;

public final class BlockedResource extends DomainException{
    public BlockedResource(String message) {
        super(message);
    }
}
