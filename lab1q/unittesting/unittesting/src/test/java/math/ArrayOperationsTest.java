package math;

import io.FileIO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOperationsTest {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations();
    }

    @Test
    public void findPrimesInFile() {
        FileIO fileio = new FileIO();
        String filepath = "C:\\Academics\\6th sem\\Testing\\lab1q\\unittesting\\unittesting\\src\\test\\resources\\grades_valid.txt";
        MyMath myMath = new MyMath();
        int[] expectedArray = {3, 2, 3};
        assertArrayEquals(expectedArray, arrayOperations.findPrimesInFile(fileio, filepath, myMath));
    }
}