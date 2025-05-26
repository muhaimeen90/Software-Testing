package math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {
    private ArithmeticOperations arithmeticOperations;

    @Before
    public void setUp() throws Exception {
        arithmeticOperations = new ArithmeticOperations();
    }

    @Test
    public void divide() {
        assertEquals(10, arithmeticOperations.divide(100, 10), 0.001);
    }

    @Test
    public void multiply() {
        assertEquals(100, arithmeticOperations.multiply(10, 10));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        arithmeticOperations.divide(10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMultiply() {
        arithmeticOperations.multiply(-10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverflowMultiply() {
        arithmeticOperations.multiply(Integer.MAX_VALUE, 2);
    }
}