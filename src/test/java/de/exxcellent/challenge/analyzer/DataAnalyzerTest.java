package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.dummies.DummyRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DataAnalyzer class.
 * This class tests the functionality of finding the records with the smallest difference.
 */
public class DataAnalyzerTest {

    private DataAnalyzer<DataRecord> analyzer;

    @BeforeEach
    void setUp() {
        this.analyzer = new DataAnalyzer<>();
    }

    @Test
    void testFindAllWithSmallestDiff_returnsSingleRecord() {
        List<DataRecord> records = List.of(
                new DummyRecord("A", 10),
                new DummyRecord("B", 5),
                new DummyRecord("C", 8)
        );

        List<DataRecord> result = analyzer.findAllWithSmallestDiff(records);

        assertEquals(1, result.size());
        assertEquals("B", result.get(0).getLabel());
        assertEquals(5, result.get(0).getDiff());
    }

    @Test
    void testFindAllWithSmallestDiff_returnsMultipleRecords() {
        List<DataRecord> records = List.of(
                new DummyRecord("A", 5),
                new DummyRecord("B", 5),
                new DummyRecord("C", 8)
        );

        List<DataRecord> result = analyzer.findAllWithSmallestDiff(records);

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(r -> r.getLabel().equals("A")));
        assertTrue(result.stream().anyMatch(r -> r.getLabel().equals("B")));
    }

    @Test
    void testFindAllWithSmallestDiff_throwsExceptionWhenEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> analyzer.findAllWithSmallestDiff(List.of()));
    }

    @Test
    void testFindAllWithSmallestDiff_throwsExceptionWhenNull() {
        assertThrows(IllegalArgumentException.class,
                () -> analyzer.findAllWithSmallestDiff(null));
    }
}
