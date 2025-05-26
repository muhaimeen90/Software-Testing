package io;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIOTest {
    private FileIO fileio;

    @Before
    public void setUp() throws Exception {
        fileio = new FileIO();
    }

    @Test
    public void readFile() {
        String filepath = "C:\\Academics\\6th sem\\Testing\\lab1q\\unittesting\\unittesting\\src\\test\\resources\\grades_valid.txt";
        int[] expectedArray = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
        assertArrayEquals(expectedArray, fileio.readFile(filepath));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFileInvalidPath() {
        String invalidPath = "invalid_path.txt";
        fileio.readFile(invalidPath);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testReadEmptyFile() {
        String filePath = "C:\\Academics\\6th sem\\Testing\\lab1q\\unittesting\\unittesting\\src\\test\\resources\\empty_file.txt";
        fileio.readFile(filePath);
    }

    @Test (expected = NumberFormatException.class)
    public void testReadFileWithInvalidNumber() {
        String filePath = "C:\\Academics\\6th sem\\Testing\\lab1q\\unittesting\\unittesting\\src\\test\\resources\\grades_invalid.txt";
        fileio.readFile(filePath);
    }

}