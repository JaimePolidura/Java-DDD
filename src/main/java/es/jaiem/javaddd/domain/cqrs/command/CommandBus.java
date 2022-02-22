package es.jaiem.javaddd.domain.cqrs.command;

public interface CommandBus {
    void dispatch(Command command);
}
