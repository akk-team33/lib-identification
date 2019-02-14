package de.team33.test.identification.v1;

import de.team33.libs.identification.v1.Key;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyTest {

    private static final Key<String> KEY = new Key<String>() {
    };

    @Test
    public final void to_String() {
        assertEquals("de.team33.test.identification.v1.KeyTest.<clinit>(KeyTest.java:10)", KEY.toString());
    }
}
