package es.jaime.javaddd.application.cache.utils;

import java.util.Collection;
import java.util.Iterator;

public final class CircularIterator<T> implements Iterator<T> {
    private final Collection<T> collection;
    private Iterator<T> actualIterator;

    public CircularIterator(Collection<T> collection) {
        this.collection = collection;
        this.actualIterator = collection.iterator();
    }

    @Override
    public T next() {
        if(!this.actualIterator.hasNext()){
            this.actualIterator = this.collection.iterator();
        }

        return this.actualIterator.next();
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
