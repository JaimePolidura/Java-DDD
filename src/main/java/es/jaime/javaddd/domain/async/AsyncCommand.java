package es.jaime.javaddd.domain.async;

public interface AsyncCommand extends AsyncMessage {
    @Override
    default AsyncMessageType type() {
        return AsyncMessageType.COMMAND;
    }
}
