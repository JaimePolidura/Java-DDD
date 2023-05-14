package es.jaime.javaddd.application.cache;

import es.jaime.javaddd.application.cache.utils.MUCache;
import es.jaime.javaddd.domain.cache.Cache;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MUCacheTest {

    @Test
    public void shouldEvict() {
        Cache<String, Integer> cache = new MUCache<>(5);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        Assertions.assertThat(cache.find("a")).isNotEmpty().hasValue(1);
        Assertions.assertThat(cache.find("b")).isNotEmpty().hasValue(2);
        Assertions.assertThat(cache.find("c")).isNotEmpty().hasValue(3);
        Assertions.assertThat(cache.find("d")).isNotEmpty().hasValue(4);
        Assertions.assertThat(cache.find("e")).isNotEmpty().hasValue(5);
        Assertions.assertThat(cache.find("f")).isEmpty();

        cache.put("f", 6);

        Assertions.assertThat(cache.find("f")).isNotEmpty().hasValue(6);
        Assertions.assertThat(cache.find("a")).isEmpty();

        cache.put("z", 610);
        cache.put("g", 7);
        Assertions.assertThat(cache.find("z")).isEmpty(); //Deberia quitar a z ya que no ha sido usada
    }

    @Test
    public void shouldInvalidate() {
        Cache<String, Integer> cache = new MUCache<>(5);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        cache.invalidate("d");

        Assertions.assertThat(cache.find("a")).isNotEmpty().hasValue(1);
        Assertions.assertThat(cache.find("b")).isNotEmpty().hasValue(2);
        Assertions.assertThat(cache.find("c")).isNotEmpty().hasValue(3);
        Assertions.assertThat(cache.find("d")).isEmpty();
        Assertions.assertThat(cache.find("e")).isNotEmpty().hasValue(5);

        cache.put("f", 6);

        Assertions.assertThat(cache.find("a")).isNotEmpty().hasValue(1);
        Assertions.assertThat(cache.find("b")).isNotEmpty().hasValue(2);
        Assertions.assertThat(cache.find("c")).isNotEmpty().hasValue(3);
        Assertions.assertThat(cache.find("f")).isNotEmpty().hasValue(6);
        Assertions.assertThat(cache.find("e")).isNotEmpty().hasValue(5);
    }

    @Test
    public void testInvalidateAll() {
        Cache<String, Integer> cache = new MUCache<>(5);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);

        cache.invalidateAll();

        Assertions.assertThat(cache.find("a")).isEmpty();
        Assertions.assertThat(cache.find("b")).isEmpty();
        Assertions.assertThat(cache.find("c")).isEmpty();
        Assertions.assertThat(cache.find("f")).isEmpty();
        Assertions.assertThat(cache.find("e")).isEmpty();
    }
}
