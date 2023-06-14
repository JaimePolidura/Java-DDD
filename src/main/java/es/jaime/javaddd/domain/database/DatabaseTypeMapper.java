package es.jaime.javaddd.domain.database;

public interface DatabaseTypeMapper {
    <T> String map(T other);
}
