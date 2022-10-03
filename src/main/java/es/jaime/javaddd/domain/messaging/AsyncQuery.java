package es.jaime.javaddd.domain.messaging;

public interface AsyncQuery extends AsyncMessage{
    @Override
    default AsyncMessageType type(){
        return AsyncMessageType.QUERY;
    }
}
