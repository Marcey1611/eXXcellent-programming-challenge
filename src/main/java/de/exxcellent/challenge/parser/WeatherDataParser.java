package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.factory.WeatherRecordFactory;
import de.exxcellent.challenge.model.WeatherDataRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * WeatherDataParser is responsible for parsing weather data records from a list of strings.
 * It extracts relevant information such as day, maximum temperature, and minimum temperature
 * from the input lines and creates WeatherDataRecord objects.
 */
public class WeatherDataParser implements DataParser<WeatherDataRecord> {

    private final ParserUtils parserUtils;
    private final WeatherRecordFactory recordFactory;

    private static final String COLUMN_NAME_DAY = "Day";
    private static final String COLUMN_NAME_MAX = "MxT";
    private static final String COLUMN_NAME_MIN = "MnT";

    /**
     * Constructs a WeatherDataParser with the specified ParserUtils and WeatherRecordFactory.
     *
     * @param parserUtils the utility class for parsing operations.
     * @param factory the factory to create WeatherDataRecord instances.
     */
    public WeatherDataParser(final ParserUtils parserUtils, final WeatherRecordFactory factory) {
        this.parserUtils = parserUtils;
        this.recordFactory = factory;
    }

    /**
     * Parses a list of strings representing weather data records.
     * The first line is expected to contain headers, and subsequent lines contain the actual data per day.
     *
     * @param lines the list of strings to parse.
     * @return a list of WeatherDataRecord objects created from the parsed data.
     */
    @Override
    public List<WeatherDataRecord> parseLines(final List<String> lines) {
        final List<WeatherDataRecord> result = new ArrayList<>();
        if (lines.isEmpty()) return result;

        final Map<String, Integer> columnIndexMap = parserUtils.getHeaders(lines.get(0));
        final ColumnIndexes columnIndexes = ColumnIndexes.from(columnIndexMap, COLUMN_NAME_DAY, COLUMN_NAME_MAX, COLUMN_NAME_MIN);

        for (int i = 1; i < lines.size(); i++) {
            final String line = lines.get(i).trim();
            final String[] tokens = parserUtils.getTokens(line, columnIndexes);
            final String[] selectedTokens = parserUtils.selectToken(tokens, columnIndexes);
            final WeatherDataRecord weatherDataRecord = recordFactory.createFromTokens(selectedTokens);
            result.add(weatherDataRecord);
        }
        return result;
    }
}
