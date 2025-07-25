package de.exxcellent.challenge.core;

import de.exxcellent.challenge.analyzer.ApplicationRunner;
import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.parser.DataParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class tests the functionality of the ApplicationRunner
 * to ensure it correctly processes CSV files and returns the expected results.
 */
@ExtendWith(MockitoExtension.class)
class ApplicationRunnerTest {

    @Mock
    private CsvReader csvReader;

    @Mock
    private DataParser<DataRecord> parser;

    @Mock
    private DataAnalyzer<DataRecord> analyzer;

    @InjectMocks
    private ApplicationRunner<DataRecord> runner;

    /**
     * Tests the run method of ApplicationRunner with a valid file.
     * It verifies that the file is read, parsed, and analyzed correctly,
     * and that the expected label is returned.
     */
    @Test
    void shouldReturnLabelOfSmallestDifferenceRecord() {
        final String fileName = "weather.csv";
        final List<String> fakeLines = List.of("Header", "Data1");
        final DataRecord mockRecord = mock(DataRecord.class);
        when(mockRecord.getLabel()).thenReturn("Record1");
        when(csvReader.readFile(fileName)).thenReturn(fakeLines);
        when(parser.parseLines(fakeLines)).thenReturn(List.of(mockRecord));
        when(analyzer.findAllWithSmallestDiff(List.of(mockRecord))).thenReturn(List.of(mockRecord));

        final String result = runner.run(fileName);

        assertEquals("Record1", result);
        verify(mockRecord).getLabel();
    }

    /**
     * Tests the run method of ApplicationRunner with multiple matching records.
     * It verifies that the method returns a comma-separated string of labels
     * for all records with the smallest difference.
     */
    @Test
    void shouldReturnCommaSeparatedLabelsForMultipleRecords() {
        final String fileName = "football.csv";
        final List<String> fakeLines = List.of("Header", "Line1", "Line2");
        final DataRecord record1 = mock(DataRecord.class);
        final DataRecord record2 = mock(DataRecord.class);
        when(record1.getLabel()).thenReturn("Alpha");
        when(record2.getLabel()).thenReturn("Beta");
        when(csvReader.readFile(fileName)).thenReturn(fakeLines);
        when(parser.parseLines(fakeLines)).thenReturn(List.of(record1, record2));
        when(analyzer.findAllWithSmallestDiff(List.of(record1, record2))).thenReturn(List.of(record1, record2));

        final String result = runner.run(fileName);

        assertEquals("Alpha, Beta", result);
        verify(record1).getLabel();
        verify(record2).getLabel();
    }
}
