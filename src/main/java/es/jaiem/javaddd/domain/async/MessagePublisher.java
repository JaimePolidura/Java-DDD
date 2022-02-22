package es.jaiem.javaddd.domain.async;

public interface MessagePublisher {
    void publish(String exchange, String routingKey, Message message);
}
