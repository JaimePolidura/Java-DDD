package es.jaime.javaddd.domain.messaging;

public interface AsyncMessagePublisher {
    void publish(String exchange, String routingKey, AsyncMessage message);
}
