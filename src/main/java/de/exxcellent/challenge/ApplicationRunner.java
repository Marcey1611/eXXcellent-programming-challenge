package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.parser.DataParser;

import java.util.List;

/**
 * The ApplicationRunner class is responsible for orchestrating the reading, parsing, and analyzing of data records.
 *
 * @param <T> The type of DataRecord that this runner will handle.
 */
public class ApplicationRunner<T extends DataRecord> {
    private final CsvReader csvReader;
    private final DataParser<T> parser;
    private final DataAnalyzer<T> analyzer;

    /**
     * Constructs an ApplicationRunner with the specified CsvReader, DataParser, and DataAnalyzer.
     *
     * @param csvReader The CsvReader to read CSV files.
     * @param parser    The DataParser to parse lines into data records.
     * @param analyzer  The DataAnalyzer to analyze the parsed data records.
     */
    public ApplicationRunner(final CsvReader csvReader, final DataParser<T> parser, final DataAnalyzer<T> analyzer) {
        this.csvReader = csvReader;
        this.parser = parser;
        this.analyzer = analyzer;
    }

    /**
     * Runs the application by reading a CSV file, parsing its contents into data records,
     * and finding the record with the smallest difference.
     *
     * @param fileName The name of the CSV file to read.
     * @return The label of the data record with the smallest difference.
     */
    public String run(final String fileName) {
        final List<String> lines = csvReader.readFile(fileName);
        final List<T> records = parser.parseLines(lines);
        final T result = analyzer.findWithSmallestDiff(records);
        return result.getLabel();
    }
}
