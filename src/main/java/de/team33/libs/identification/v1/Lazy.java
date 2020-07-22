package de.team33.libs.identification.v1;

import java.util.function.Supplier;

class Lazy<T> implements Supplier<T> {

    private Supplier<T> backing;

    Lazy(final Supplier<T> initial) {
        this.backing = new Supplier<T>() {
            @Override
            public synchronized T get() {
                if (backing == this) {
                    final T result = initial.get();
                    backing = () -> result;
                }
                return backing.get();
            }
        };
    }

    @Override
    public T get() {
        return backing.get();
    }
}
