package es.jaime.javaddd.domain.cqrs.command;

public interface CommandBus {
    void dispatch(Command command);
}
