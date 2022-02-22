package es.jaime.javaddd.domain.async;

public interface CommandMessage extends Message{
    @Override
    default MessageType type() {
        return MessageType.COMMAND;
    }
}
