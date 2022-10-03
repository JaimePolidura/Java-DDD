package es.jaime.javaddd.domain.networking;

public interface EmailSender {
    void send(String to, String subject, String content);
}
