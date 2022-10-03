package es.jaime.javaddd.application.cache;

import es.jaime.javaddd.domain.cache.Cache;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class MUCache<K, V> implements Cache<K, V> {
    private final LinkedList<CacheEntry<K, V>> entries;
    private final Map<K, CacheEntry<K, V>> indexedEntries;
    private final ReadWriteLock readWriteLock;
    private final int maxCapacity;

    public MUCache(int maxCapacity) {
        this.readWriteLock = new ReentrantReadWriteLock(true);
        this.indexedEntries = new HashMap<>();
        this.entries = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void put(K key, V value) {
        this.readWriteLock.writeLock().lock();
        CacheEntry<K, V> newEntry = CacheEntry.of(key, value);

        if(isCacheFull()){
            removeLeastUsed();
        }

        addEntry(newEntry);
        this.readWriteLock.writeLock().unlock();
    }

    @Override
    public Optional<V> find(K key) {
        this.readWriteLock.readLock().lock();

        CacheEntry<K, V> entry = this.indexedEntries.get(key);
        boolean cacheEntryFound = entry != null;

        if(cacheEntryFound){
            entry.incrementUseCounter();
        }

        this.readWriteLock.readLock().unlock();

        return Optional.ofNullable(entry != null ? entry.value : null);
    }

    @Override
    public boolean contains(K key) {
        return this.indexedEntries.containsKey(key);
    }

    @Override
    public void invalidate(K key) {
        this.readWriteLock.writeLock().lock();

        this.entries.removeIf(entry -> entry.key.equals(key));
        this.indexedEntries.remove(key);

        this.readWriteLock.writeLock().unlock();
    }

    @Override
    public void invalidateAll() {
        this.readWriteLock.writeLock().lock();

        this.entries.clear();
        this.indexedEntries.clear();

        this.readWriteLock.writeLock().unlock();
    }

    private boolean isCacheFull() {
        int actualSize = this.entries.size();

        return actualSize + 1 > maxCapacity;
    }

    private void addEntry(CacheEntry<K, V> entry) {
        indexedEntries.put(entry.key, entry);
        entries.addLast(entry);
    }

    private void removeLeastUsed() {
        Iterator<CacheEntry<K, V>> entriesIterator = this.entries.iterator();
        int actualIndex = 0;

        CacheEntry<K, V> leastUsedCacheEntry = entriesIterator.next();
        int leastUsesCacheEntryUseCounter = 0;

        while (entriesIterator.hasNext()) {
            CacheEntry<K, V> actualCacheEntry = entriesIterator.next();

            if(actualCacheEntry.getUseCounter() == 0){
                leastUsedCacheEntry = actualCacheEntry;
                break;
            }

            int lessUsedUseCounter = Math.min(leastUsesCacheEntryUseCounter, actualCacheEntry.getUseCounter());
            boolean foundCacheEntryLessUsed = lessUsedUseCounter != leastUsesCacheEntryUseCounter;

            if(foundCacheEntryLessUsed){
                leastUsedCacheEntry = actualCacheEntry;
                leastUsesCacheEntryUseCounter = lessUsedUseCounter;
            }
        }

        this.entries.remove(leastUsedCacheEntry);
        this.indexedEntries.remove(leastUsedCacheEntry.key);
    }

    @ToString
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class CacheEntry<K, V> implements Comparable<CacheEntry<K, V>>{
        @Getter private final K key;
        @Getter private final V value;
        private final AtomicInteger useCounter;

        public int getUseCounter() {
            return this.useCounter.get();
        }

        public void incrementUseCounter() {
            this.useCounter.incrementAndGet();
        }

        @Override
        public int compareTo(CacheEntry<K, V> o) {
            return this.getUseCounter() - o.getUseCounter();
        }

        public static <K, V> CacheEntry<K, V> of(K key, V value) {
            return new CacheEntry<>(key, value, new AtomicInteger(0));
        }
    }
}
