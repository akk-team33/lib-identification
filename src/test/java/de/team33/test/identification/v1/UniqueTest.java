package de.team33.test.identification.v1;

import de.team33.libs.identification.v1.Unique;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UniqueTest {

    private static final Unique KEY = new Unique();

    @Test
    public final void to_String() {
        assertEquals(

                "de.team33.test.identification.v1.UniqueTest.<clinit>(UniqueTest.java:12)",

                KEY.toString()

        );
    }
}
