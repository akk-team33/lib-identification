package de.team33.libs.identification.v1;

import java.util.Arrays;
import java.util.UUID;

/**
 * <p>Abstracts keys with an identity semantic that also refers to a specific type.</p>
 *
 * <p>Typically, an instance is defined as a static final field in the class of its primary application context.</p>
 *
 * <p>The string representation of an instance points to the location of its initialization in the code.</p>
 */
public class Key<T> {

    private final String representation;

    /**
     * Initializes an instance so that the {@link #toString()} method lets you infer where in the source code the
     * instance was created.
     */
    public Key() {
        this(UUID.randomUUID().toString(), Thread.currentThread().getStackTrace());
    }

    /**
     * Initializes an instance so that the {@link #toString()} method lets you infer where in the source code the
     * instance was created.
     */
    public Key(final String name) {
        this(name, Thread.currentThread().getStackTrace());
    }

    private Key(final String name, final StackTraceElement[] stackTrace) {
        final String creation = (2 < stackTrace.length)
                ? stackTrace[2].toString()
                : ("unknown(" + Arrays.toString(stackTrace) + ")");
        representation = String.format("%s(%s)", name, creation);
    }

    /**
     * Returns a string representation that allows conclusions about where in source code that instance was created.
     */
    @Override
    public final String toString() {
        return representation;
    }
}
