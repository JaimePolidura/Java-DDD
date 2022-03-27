package es.jaime.javaddd.domain.async;

public interface AsyncMessagePublisher {
    void publish(String exchange, String routingKey, AsyncMessage message);
}
