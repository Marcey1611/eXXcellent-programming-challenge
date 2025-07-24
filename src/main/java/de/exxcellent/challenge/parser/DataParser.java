package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.model.DataRecord;

import java.util.List;

/**
 * DataParser is an interface for parsing lines of data into a list of DataRecord objects.
 * It is used to convert raw data lines into structured records for further processing.
 *
 * @param <T> the type of DataRecord that this parser will produce.
 */
public interface DataParser<T extends DataRecord> {
    List<T> parseLines(final List<String> lines);
}
