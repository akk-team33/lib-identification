package de.team33.test.identification.v1;

import de.team33.libs.identification.v1.Unique;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UniqueTest {

    private static final Key KEY = new Key();

    private final Key key = new Key(this);

    @Test
    public final void testToString_staticField() {
        final String expected = "KEY @ de.team33.test.identification.v1.UniqueTest.<clinit>(UniqueTest.java:11)";
        System.out.println("expected: " + expected);
        assertEquals(expected, KEY.toString());
    }

    @Test
    public final void testToString_instanceField() {
        final String expected = "key @ de.team33.test.identification.v1.UniqueTest.<init>(UniqueTest.java:13)";
        System.out.println("expected: " + expected);
        assertEquals(expected, key.toString());
    }

    @Test
    public final void testToString_local() {
        final String expected = "<noname> @ de.team33.test.identification.v1.UniqueTest.testToString_local(UniqueTest.java:33)";
        System.out.println("expected: " + expected);
        final Key localKey = new Key(this);
        assertEquals(expected, localKey.toString());
    }

    @Test
    public final void testToString_dynamic() {
        final String expected = "<noname> @ de.team33.test.identification.v1.UniqueTest.testToString_dynamic(UniqueTest.java:41)";
        System.out.println("expected: " + expected);
        assertEquals(expected, new Key(this).toString());
    }

    @Test
    public final void testToString_named() {
        final String expected = "myExplicitName @ de.team33.test.identification.v1.UniqueTest.testToString_named(UniqueTest.java:48)";
        System.out.println("expected: " + expected);
        assertEquals(expected, new Key("myExplicitName").toString());
    }
}
