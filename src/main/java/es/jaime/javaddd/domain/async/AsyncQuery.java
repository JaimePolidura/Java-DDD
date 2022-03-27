package es.jaime.javaddd.domain.async;

public interface AsyncQuery extends AsyncMessage{
    @Override
    default AsyncMessageType type(){
        return AsyncMessageType.QUERY;
    }
}
