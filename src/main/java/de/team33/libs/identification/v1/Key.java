package de.team33.libs.identification.v1;

/**
 * <p>Abstracts keys with an identity semantic that also refers to a specific type.</p>
 *
 * <p>Typically, an instance is defined as a static final field in the class of its primary application context.</p>
 *
 * <p>The string representation of an instance points to the location of its initialization in the code.</p>
 */
@Deprecated
public class Key<T> extends Unique {
}
