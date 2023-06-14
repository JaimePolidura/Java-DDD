package es.jaime.javaddd.domain.database;

public interface DatabaseTypeMapper {
    <T> String map(Class<T> otherClass, T other);
}
