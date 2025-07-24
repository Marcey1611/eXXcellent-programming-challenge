package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.DataRecord;

import java.util.List;

/**
 * A generic class for analyzing data records.
 * This class provides methods to find the record with the smallest difference.
 *
 * @param <T> the type of DataRecord to analyze
 */
public class DataAnalyzer<T extends DataRecord> {

    /**
     * Finds the record with the smallest difference from a list of records.
     *
     * @param records the list of records to analyze
     * @return the record with the smallest difference
     * @throws IllegalArgumentException if the list is null or empty
     */
    public List<T> findAllWithSmallestDiff(final List<T> records) {
        final int minDiff = records.stream()
                .mapToInt(DataRecord::getDiff)
                .min()
                .orElseThrow();

        return records.stream()
                .filter(r -> r.getDiff() == minDiff)
                .toList();
    }
}
