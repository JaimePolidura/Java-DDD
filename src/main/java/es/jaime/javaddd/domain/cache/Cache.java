package es.jaime.javaddd.domain.cache;

import java.util.Optional;

public interface Cache<K, V> {
    void put(K key, V value);

    Optional<V> find(K key);

    boolean contains(K key);

    void invalidate(K key);

    void invalidateAll();
}
