package de.team33.test.identification.v1;

import static org.junit.Assert.assertEquals;

import de.team33.libs.identification.v1.KeySupplier;
import org.junit.Test;

public class KeySupplierTest
{

    private static final KeySupplier<String> KEY = new KeySupplier<>(() -> null);

    @Test
    public final void to_String() {
        assertEquals("de.team33.test.identification.v1.KeySupplierTest.<clinit>(KeySupplierTest.java:11)",
                     KEY.toString());
    }
}
