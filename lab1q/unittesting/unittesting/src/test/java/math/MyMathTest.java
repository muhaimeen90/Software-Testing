package math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {
    private MyMath myMath;

    @Before
    public void setUp() throws Exception {
        myMath = new MyMath();
    }

    @Test
    public void factorial() {
        assertEquals(6, myMath.factorial(3));
    }

    @Test
    public void isPrime() {
        assertTrue(myMath.isPrime(7));
        assertFalse(myMath.isPrime(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        myMath.factorial(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialTooLarge() {
        myMath.factorial(13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrimeLessThan2() {
        myMath.isPrime(1);
    }
}