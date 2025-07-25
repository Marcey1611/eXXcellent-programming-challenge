package de.exxcellent.challenge.core;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.parser.DataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ApplicationRunner class is responsible for orchestrating the reading, parsing, and analyzing of data records.
 *
 * @param <T> The type of DataRecord that this runner will handle.
 */
public class ApplicationRunner<T extends DataRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunner.class);

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
        LOGGER.debug("Read {} lines from file '{}'.", lines.size(), fileName);

        final List<T> records = parser.parseLines(lines);
        LOGGER.debug("Parsed {} records from file '{}'.", records.size(), fileName);

        final List<T> smallestDiffRecords = analyzer.findAllWithSmallestDiff(records);
        LOGGER.debug("Found {} record(s) with smallest difference.", smallestDiffRecords.size());

        return smallestDiffRecords.stream()
                .map(DataRecord::getLabel)
                .collect(Collectors.joining(", "));
    }
}
