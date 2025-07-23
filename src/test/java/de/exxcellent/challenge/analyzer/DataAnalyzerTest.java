package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.model.DummyRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the DataAnalyzer class.
 * This class tests the functionality of finding the record with the smallest difference.
 */
public class DataAnalyzerTest {

    private DataAnalyzer<DataRecord> analyzer;

    /**
     * Sets up the DataAnalyzer instance before each test.
     */
    @BeforeEach
    void setUp() {
        this.analyzer = new DataAnalyzer<>();
    }

    /**
     * Tests the findWithSmallestDiff method with a list of DataRecord objects.
     * It verifies that the method correctly identifies the record with the smallest difference.
     */
    @Test
    void testFindWithSmallestDiff_returnsCorrectRecord() {
        final List<DataRecord> records = List.of(
                new DummyRecord("A", 10),
                new DummyRecord("B", 5),
                new DummyRecord("C", 8)
        );

        final DataRecord result = this.analyzer.findWithSmallestDiff(records);

        assertEquals("B", result.label());
        assertEquals(5, result.diff());
    }

    /**
     * Tests the findWithSmallestDiff method with a list containing no records.
     * It verifies that the method throws an IllegalArgumentException when the list is empty.
     */
    @Test
    void testFindWithSmallestDiff_throwsExceptionWhenEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> this.analyzer.findWithSmallestDiff(List.of()));
    }

    /**
     * Tests the findWithSmallestDiff method with a null list.
     * It verifies that the method throws an IllegalArgumentException when the input is null.
     */
    @Test
    void testFindWithSmallestDiff_throwsExceptionWhenNull() {
        assertThrows(IllegalArgumentException.class,
                () -> this.analyzer.findWithSmallestDiff(null));
    }
}
