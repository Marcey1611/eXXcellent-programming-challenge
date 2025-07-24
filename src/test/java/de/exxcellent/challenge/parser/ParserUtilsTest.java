package de.exxcellent.challenge.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ParserUtils class.
 * This class tests the methods responsible for parsing headers and tokens from weather data records.
 */
@ExtendWith(MockitoExtension.class)
class ParserUtilsTest {

    @InjectMocks
    private ParserUtils parserUtils;

    /**
     * Tests the getHeaders method to ensure it correctly parses the header line
     * and returns a map of header names to their respective column indexes.
     */
    @Test
    void testGetHeaders_parsesHeaderCorrectly() {
        final String firstLine = "Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP";

        final Map<String, Integer> result = parserUtils.getHeaders(firstLine);

        assertEquals(0, result.get("Day"));
        assertEquals(1, result.get("MxT"));
        assertEquals(2, result.get("MnT"));
        assertEquals(3, result.get("AvT"));
        assertEquals(4, result.get("AvDP"));
        assertEquals(5, result.get("1HrP TPcpn"));
        assertEquals(6, result.get("PDir"));
        assertEquals(7, result.get("AvSp"));
        assertEquals(8, result.get("Dir"));
        assertEquals(9, result.get("MxS"));
        assertEquals(10, result.get("SkyC"));
        assertEquals(11, result.get("MxR"));
        assertEquals(12, result.get("Mn"));
        assertEquals(13, result.get("R AvSLP"));
    }

    /**
     * Tests the getTokens method to ensure it correctly splits a valid line of weather data
     * into an array of tokens.
     */
    @Test
    void testGetTokens_validLine_returnsCorrectTokens() {
        final String[] tokens = parserUtils.getTokens("1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5", new ColumnIndexes(0, 1, 2));

        assertEquals("1", tokens[0]);
        assertEquals("88", tokens[1]);
        assertEquals("59", tokens[2]);
        assertEquals("74", tokens[3]);
        assertEquals("53.8", tokens[4]);
        assertEquals("0", tokens[5]);
        assertEquals("280", tokens[6]);
        assertEquals("9.6", tokens[7]);
        assertEquals("270", tokens[8]);
        assertEquals("17", tokens[9]);
        assertEquals("1.6", tokens[10]);
        assertEquals("93", tokens[11]);
        assertEquals("23", tokens[12]);
        assertEquals("1004.5", tokens[13]);
        assertEquals(14, tokens.length);
    }

    /**
     * Tests the getTokens method to ensure it throws an exception when the input line is too short.
     */
    @Test
    void testGetTokens_tooShortLine_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parserUtils.getTokens("1,88", new ColumnIndexes(0, 1, 2));
        });
    }

    /**
     * Tests the selectToken method to ensure it correctly selects tokens based on the provided column indexes.
     */
    @Test
    void testSelectToken_validInput_returnsSelectedTokens() {
        final String[] tokens = {"1", "88", "59", "extra"};
        final ColumnIndexes indexes = new ColumnIndexes(0, 1, 2);

        final String[] result = parserUtils.selectToken(tokens, indexes);

        assertArrayEquals(new String[]{"1", "88", "59"}, result);
    }

    /**
     * Tests the selectToken method to ensure it throws an exception when the provided column indexes are invalid.
     */
    @Test
    void testSelectToken_invalidIndex_throwsException() {
        final String[] tokens = {"1", "88", "59"};
        final ColumnIndexes indexes = new ColumnIndexes(0, 1, 3);

        assertThrows(IllegalArgumentException.class,
                () -> parserUtils.selectToken(tokens, indexes));
    }
}