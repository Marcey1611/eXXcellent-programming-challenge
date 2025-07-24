package de.exxcellent.challenge.parser;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ColumnIndexes class.
 * This class verifies the functionality of the from method and checks for exceptions when required keys are missing.
 */
class ColumnIndexesTest {

    /**
     * Tests the from method of ColumnIndexes to ensure it correctly creates an instance with the specified indexes.
     */
    @Test
    void testFrom_validMap_createsIndexes() {
        final Map<String, Integer> map = Map.of("Day", 0, "MxT", 1, "MnT", 2);
        final ColumnIndexes indexes = ColumnIndexes.from(map, "Day", "MxT", "MnT");
        assertEquals(0, indexes.label());
        assertEquals(1, indexes.base());
        assertEquals(2, indexes.substrate());
    }

    /**
     * Tests the from method of ColumnIndexes to ensure it throws an exception when a required key is missing.
     */
    @Test
    void testFrom_missingKey_throwsException() {
        final Map<String, Integer> map = Map.of("Day", 0, "MxT", 1);
        assertThrows(IllegalArgumentException.class, () -> ColumnIndexes.from(map, "Day", "MxT", "MnT"));
    }
}
