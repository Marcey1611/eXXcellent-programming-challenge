package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.errorhandling.InvalidLineFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for parsing CSV-like data.
 * Provides methods to extract headers, tokens, and select specific tokens based on column indexes.
 */
public class ParserUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserUtils.class);

    /**
     * Extracts headers from the first line of a CSV-like string.
     *
     * @param firstLine the first line of the CSV-like string, containing headers.
     * @return a map where keys are header names and values are their respective column indexes.
     */
    protected Map<String, Integer> getHeaders(final String firstLine) {
        final String[] headers = firstLine.trim().split(",");
        final Map<String, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            columnIndexMap.put(headers[i].trim(), i);
        }
        return columnIndexMap;
    }

    /**
     * Extracts tokens from a given line of CSV-like data.
     *
     * @param line the line to be split into tokens.
     * @param columnIndexes the indexes of the columns to be extracted.
     * @return an array of tokens extracted from the line.
     */
    protected String[] getTokens(final String line, final ColumnIndexes columnIndexes) {
        final String[] tokens = line.split(",");
        final int maxRequiredIndex = Math.max(
                columnIndexes.label(),
                Math.max(columnIndexes.base(), columnIndexes.substrate())
        );

        if (tokens.length <= maxRequiredIndex) {
            LOGGER.error("CSV line '{}' is too short. Expected at least {} columns, but got {}", line, maxRequiredIndex + 1, tokens.length);
            throw new InvalidLineFormatException("Line of provided file does not contain enough columns.");
        }
        return tokens;
    }

    /**
     * Selects specific tokens from an array based on the provided column indexes.
     *
     * @param tokens the array of tokens to select from.
     * @param columnIndexes the indexes of the columns to be selected.
     * @return an array containing the selected tokens.
     */
    protected String[] selectToken(final String[] tokens, final ColumnIndexes columnIndexes) {
        try {
            return new String[]{
                    tokens[columnIndexes.label()],
                    tokens[columnIndexes.base()],
                    tokens[columnIndexes.substrate()],
            };
        } catch (final Exception exception) {
            LOGGER.error("Failed to select tokens at indexes {} from line: {}", columnIndexes, List.of(tokens), exception);
            throw new InvalidLineFormatException("Error extracting selected tokens from a line.");
        }
    }
}
