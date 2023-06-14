package es.jaime.javaddd.domain.database;

public interface DatabaseTypeMapper {
    <T> String toDatabase(T other);
}
