package de.team33.libs.identification.v1;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Implements instances with an identity semantics and a {@linkplain #toString() string representation} that
 * points to the location of their instantiation in the code.
 */
public class Unique {

    private final String codeLocation;
    private final Class<?> declaringClass;
    private final Object contextInstance;
    private final Supplier<String> stringValue;

    /**
     * <p>Initializes a new instance. This variant is primarily used to initialize static instances if their
     * field name is to be part of the {@linkplain #toString() string representation} of the new instance.
     * Example:</p>
     * <pre>
     *  public class Context {
     *
     *      public static final Key NAME_KEY = new Key();
     *      public static final Key BIRTH_KEY = new Key();
     *      public static final Key LOCATION_KEY = new Key();
     *
     *      // ...
     *
     *      public static class Key extends Unique {
     *          // ...
     *      }
     *  }
     * </pre>
     */
    public Unique() {
        this(null);
    }

    /**
     * <p>Initializes a new instance. This variant is primarily used to initialize instances if an explicit
     * given name is to be part of the {@linkplain #toString() string representation} of the new instance.
     * Example:</p>
     * <pre>
     *  public class Context {
     *
     *      public static final Key NAME_KEY = new Key("name_key");
     *
     *      public final Key birthKey = new Key("birth_key");
     *
     *      public void aMethod() {
     *          final Key locationKey = new Key("location_key");
     *          // ...
     *      }
     *
     *      // ...
     *
     *      public static class Key extends Unique {
     *          // ...
     *      }
     *  }
     * </pre>
     */
    public Unique(final String name) {
        this((Object) name);
    }

    /**
     * <p>Initializes a new instance. This variant is primarily used to initialize fields within a context instances if
     * their field name is to be part of the {@linkplain #toString() string representation} of the new instance. Example:</p>
     * <pre>
     *  public class Context {
     *
     *      public final Key nameKey = new Key(this);
     *      public final Key birthKey = new Key(this);
     *      public final Key locationKey = new Key(this);
     *
     *      // ...
     *
     *      public static class Key extends Unique {
     *          // ...
     *      }
     *  }
     * </pre>
     *
     * @param contextInstance An instance in the context of which this instance is to be defined, or {@code null}
     *                        if this instance is to be defined statically.
     */
    public Unique(final Object contextInstance) {
        this.contextInstance = contextInstance;
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final String className = getClass().getName();
        final Predicate<StackTraceElement> predicate = ste ->
                className.equals(ste.getClassName()) && "<init>".equals(ste.getMethodName());
        final int index1 = indexOf(stackTrace, 0, predicate);
        final int index2 = indexOf(stackTrace, index1, predicate.negate());
        if (0 > index2) {
            this.codeLocation = "unknown code (unknown location)";
            this.declaringClass = null;
        } else {
            final StackTraceElement stackTraceElement = stackTrace[index2];
            this.codeLocation = " @ " + stackTraceElement;
            this.declaringClass = declaringClassFrom(stackTraceElement);
        }
        final Supplier<String> initialStringValue = contextInstance instanceof String
                ? () -> contextInstance + codeLocation
                : this::newStringValue;
        stringValue = new Lazy<>(initialStringValue);
    }

    private static Class<?> declaringClassFrom(final StackTraceElement stackTraceElement) {
        try {
            return Class.forName(stackTraceElement.getClassName());
        } catch (final Exception ignored) {
            return null;
        }
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

    private String newStringValue() {
        return Optional.ofNullable(declaringClass)
                       .map(Class::getDeclaredFields)
                       .map(Stream::of)
                       .orElseGet(Stream::empty)
                       .peek(field -> field.setAccessible(true))
                       .filter(this::fieldFilter)
                       .map(Field::getName)
                       .findAny()
                       .orElse("<noname>") + codeLocation;
    }

    private boolean fieldFilter(final Field field) {
        try {
            return Unique.this == field.get(contextInstance);
        } catch (final Exception ignored) {
            return false;
        }
    }

    /**
     * This implementation provides an identity hash code.
     */
    @Override
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    /**
     * Only identical instances are equal.
     */
    @Override
    public final boolean equals(final Object obj) {
        return this == obj;
    }

    /**
     * Returns a string representation that allows conclusions about where in source code that instance was created.
     */
    @Override
    public final String toString() {
        return stringValue.get();
    }
}
