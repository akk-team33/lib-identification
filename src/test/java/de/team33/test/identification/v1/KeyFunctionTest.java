package de.team33.test.identification.v1;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import de.team33.libs.identification.v1.KeyFunction;
import org.junit.Test;

public class KeyFunctionTest {

    private static KeyFunction<KeyFunctionTest, Date> DATE = new KeyFunction<>(KeyFunctionTest::newDate);

    private long time = System.currentTimeMillis();

    private Date newDate() {
        return new Date(time);
    }

    @Test
    public final void to_String() {
        assertEquals(
                "de.team33.test.identification.v1.KeyFunctionTest.<clinit>(KeyFunctionTest.java:12)",
                DATE.toString()
        );
    }

    @Test
    public final void apply() {
        assertEquals(time, DATE.apply(this).getTime());
    }
}
