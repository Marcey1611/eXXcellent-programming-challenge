package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.DataRecord;

import java.util.Collections;
import java.util.Comparator;
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
    public T findWithSmallestDiff(final List<T> records) {
        if (records == null || records.isEmpty()) {
            //TODO: Implement custom error handling
            throw new IllegalArgumentException("No records provided.");
        }

        final Comparator<T> byDiff = Comparator.comparingInt(DataRecord::getDiff);
        return Collections.min(records, byDiff);
    }
}
