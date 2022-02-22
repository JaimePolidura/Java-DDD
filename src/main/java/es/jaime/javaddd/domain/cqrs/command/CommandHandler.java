package es.jaime.javaddd.domain.cqrs.command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
