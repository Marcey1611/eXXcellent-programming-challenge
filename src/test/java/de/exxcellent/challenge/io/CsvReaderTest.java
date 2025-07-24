package de.exxcellent.challenge.io;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CsvReader class.
 * This class tests the readFile method to ensure it correctly reads lines from a CSV file.
 */
class CsvReaderTest {

    /**
     * Tests the readFile method of the CsvReader class.
     * It checks if the method reads the correct number of lines and verifies the content of those lines.
     *
     * @throws URISyntaxException if the resource URL cannot be converted to a URI.
     */
    @Test
    void testReadFile_returnsLinesFromTestFile() throws URISyntaxException {
        final CsvReader reader = new CsvReader();
        final URL resource = getClass().getClassLoader().getResource("weather-test.csv");
        assertNotNull(resource);

        final String filePath = Paths.get(resource.toURI()).toString();
        final List<String> lines = reader.readFile(filePath);

        assertEquals(4, lines.size());
        assertTrue(lines.get(0).contains("Day,MxT,MnT"));
        assertTrue(lines.get(1).contains("1,88,59"));
        assertTrue(lines.get(2).contains("2,79,63"));
        assertTrue(lines.get(3).contains("3,77,55"));
    }
}
