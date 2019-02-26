package de.team33.libs.identification.v1;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Defines instances with an identity semantic and a string representation that points to the location
 * of its initialization in the code.
 */
public class Unique {

    private final String representation;

    /**
     * Initializes an instance so that the {@link #toString()} method lets you infer where in the source code the
     * instance was created.
     */
    public Unique() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final String className = getClass().getName();
        final Predicate<StackTraceElement> predicate = ste -> className.equals(ste.getClassName());
        final int index1 = indexOf(stackTrace, 0, predicate);
        final int index2 = indexOf(stackTrace, index1, predicate.negate());
        this.representation =
                (0 > index2) ? ("unknown(" + Arrays.toString(stackTrace) + ")") : stackTrace[index2].toString();
    }

    private static int indexOf(final StackTraceElement[] stackTrace,
                               final int start,
                               final Predicate<StackTraceElement> predicate) {
        for (int index = start; 0 <= index && index < stackTrace.length; ++index) {
            if (predicate.test(stackTrace[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * This implementation uses and manifests the basic implementation, providing an identity hash code.
     */
    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * This implementation uses and manifests the basic implementation.
     * In this respect, only identical instances are equal.
     */
    @Override
    public final boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns a string representation that allows conclusions about where in source code that instance was created.
     */
    @Override
    public final String toString() {
        return representation;
    }
}
