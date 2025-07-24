package de.exxcellent.challenge.parser;

import java.util.Map;

/**
 * Represents the indexes of columns in a data structure.
 * This record holds the indexes for label, base, and substrate columns.
 */
public record ColumnIndexes(int label, int base, int substrate) {

    /**
     * Creates a new instance of ColumnIndexes from a map of column names to their indexes.
     *
     * @param map A map where keys are column names and values are their corresponding indexes.
     * @param label The name of the label column.
     * @param base The name of the base column.
     * @param substrate The name of the substrate column.
     * @return A new instance of ColumnIndexes with the specified indexes.
     * @throws IllegalArgumentException if any of the specified columns are not found in the map.
     */
    public static ColumnIndexes from(final Map<String, Integer> map, final String label, final String base, final String substrate) {
        try {
            final Integer labelIndex = map.get(label);
            final Integer baseIndex = map.get(base);
            final Integer substrateIndex = map.get(substrate);
            return new ColumnIndexes(labelIndex, baseIndex, substrateIndex);
        } catch (final Exception exception) {
            //TODO: Implement custom error handling
            throw new IllegalArgumentException("Column names not found in the provided map: " + exception.getMessage());
        }
    }
}
