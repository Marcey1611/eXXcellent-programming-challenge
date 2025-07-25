package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.factory.FootballRecordFactory;
import de.exxcellent.challenge.model.FootballDataRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the FootballDataParser class.
 * This class tests the parsing of football data records from a list of strings.
 */
@ExtendWith(MockitoExtension.class)
class FootballDataParserTest {

    @Mock
    private FootballRecordFactory factory;

    @InjectMocks
    private ParserUtils parserUtils;

    /**
     * Tests the parseLines method with valid input.
     */
    @Test
    void testParseLines_validInput_createsCorrectRecords() {
        final List<String> lines = List.of("Team,Goals,Goals Allowed", "1,88,59", "2,79,63");

        when(factory.createFromTokens(new String[]{"1", "88", "59"})).thenReturn(new FootballDataRecord("1", 88, 59));
        when(factory.createFromTokens(new String[]{"2", "79", "63"})).thenReturn(new FootballDataRecord("2", 79, 63));

        final FootballDataParser parser = new FootballDataParser(parserUtils, factory);
        final List<FootballDataRecord> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getLabel());
        assertEquals(29, result.get(0).getDiff());
    }

    /**
     * Tests the parseLines method with input that contains an empty line.
     */
    @Test
    void testParseLines_emptyInput_returnsEmptyList() {
        final FootballDataParser parser = new FootballDataParser(parserUtils, factory);
        final List<FootballDataRecord> result = parser.parseLines(List.of());
        assertTrue(result.isEmpty());
    }
}