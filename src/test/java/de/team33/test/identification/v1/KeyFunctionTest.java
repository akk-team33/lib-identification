package de.team33.test.identification.v1;

import de.team33.libs.identification.v1.KeyFunction;
import org.junit.Test;

import java.util.Date;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;


public class KeyFunctionTest {

    private static Key<Date> DATE = new Key<>(KeyFunctionTest::newDate);

    private long time = System.currentTimeMillis();

    private Date newDate() {
        return new Date(time);
    }

    @Test
    public final void to_String() {
        assertEquals("de.team33.test.identification.v1.KeyFunctionTest.<clinit>(KeyFunctionTest.java:15)",
                DATE.toString());
    }

    @Test
    public final void apply() {
        assertEquals(time, DATE.apply(this).getTime());
    }

    private static class Key<R> extends KeyFunction<KeyFunctionTest, R> {

        private Key(final Function<KeyFunctionTest, R> inner) {
            super(inner);
        }
    }
}
