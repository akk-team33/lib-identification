package de.team33.libs.identification.v1;

import java.util.function.Function;

public class KeyFunction<T, R> extends Unique implements Function<T, R> {

    private final Function<T, R> inner;

    public KeyFunction(final Function<T, R> inner) {
        this.inner = inner;
    }

    @Override
    public final R apply(final T t) {
        return inner.apply(t);
    }
}
