package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;
    private HashSet<E> showed = new HashSet<>();
    private Optional<E> nextItem = Optional.empty();

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        if (this.iterator.hasNext()) {
            E next = this.iterator.next();
            this.showed.add(next);
            this.nextItem = Optional.of(next);
        }
    }

    @Override
    public boolean hasNext() {
        return this.nextItem.isPresent();
    }

    @Override
    public E next() {
        E e = this.nextItem.get();
        this.nextItem = Optional.empty();
        while (this.iterator.hasNext()) {
            E next = this.iterator.next();
            if (!this.showed.contains(next)) {
                this.showed.add(next);
                this.nextItem = Optional.of(next);
                break;
            }
        }
        return e;
    }
    // --end->
}