package de.team33.libs.identification.v1;

import java.util.function.Function;

/**
 * Implements a {@link Function} that also represents a unique identification key.
 */
public class KeyFunction<T, R> extends Unique implements Function<T, R> {

    private final Function<T, R> delegate;

    public KeyFunction(final Function<T, R> delegate) {
        this.delegate = delegate;
    }

    @Override
    public final R apply(final T t) {
        return delegate.apply(t);
    }
}
