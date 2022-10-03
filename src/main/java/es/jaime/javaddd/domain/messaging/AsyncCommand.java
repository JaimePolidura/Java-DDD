package es.jaime.javaddd.domain.messaging;

public interface AsyncCommand extends AsyncMessage {
    @Override
    default AsyncMessageType type() {
        return AsyncMessageType.COMMAND;
    }
}
