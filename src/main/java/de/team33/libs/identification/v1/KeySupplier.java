package de.team33.libs.identification.v1;

import java.util.function.Supplier;

/**
 * Implements a {@link Supplier} that also represents a unique identification key.
 *
 * @see Unique
 */
public class KeySupplier<R> extends Unique implements Supplier<R> {

    private final Supplier<R> delegate;

    /**
     * Initializes a new instance with a given {@code delegate}.
     */
    public KeySupplier(final Supplier<R> delegate) {
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final R get() {
        return delegate.get();
    }
}
