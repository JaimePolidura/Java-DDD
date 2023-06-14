package es.jaime.javaddd.domain.database;

public interface DatabaseTypeSerializer {
    <T> String serialize(T other);
}
