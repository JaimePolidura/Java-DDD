package es.jaime.javaddd.domain.messaging;

public interface AsyncDomainEvent extends AsyncMessage {
    @Override
    default AsyncMessageType type(){
        return AsyncMessageType.EVENT;
    }
}
