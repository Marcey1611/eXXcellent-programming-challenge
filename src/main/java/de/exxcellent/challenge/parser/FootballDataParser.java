package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.factory.FootballRecordFactory;
import de.exxcellent.challenge.model.FootballDataRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FootballDataParser is responsible for parsing football data records from a list of strings.
 * It extracts relevant information such as team name, goals, and goals allowed
 * from the input lines and creates FootballDataRecord objects.
 */
public class FootballDataParser implements DataParser<FootballDataRecord> {

    private static final String COLUMN_NAME_TEAM = "Team";
    private static final String COLUMN_NAME_GOALS = "Goals";
    private static final String COLUMN_NAME_GOALS_ALLOWED = "Goals Allowed";

    private final ParserUtils parserUtils;
    private final FootballRecordFactory recordFactory;

    /**
     * Constructs a WeatherDataParser with the specified ParserUtils and WeatherRecordFactory.
     *
     * @param parserUtils the utility class for parsing operations.
     * @param recordFactory the factory to create FootballDataRecord instances.
     */
    public FootballDataParser(ParserUtils parserUtils, FootballRecordFactory recordFactory) {
        this.parserUtils = parserUtils;
        this.recordFactory = recordFactory;
    }

    /**
     * Parses a list of strings representing football data records.
     * The first line is expected to contain headers, and subsequent lines contain the actual data per team.
     *
     * @param lines the list of strings to parse.
     * @return a list of FootballDataRecord objects created from the parsed data.
     */
    @Override
    public List<FootballDataRecord> parseLines(final List<String> lines) {
        final List<FootballDataRecord> result = new ArrayList<>();
        if (lines.isEmpty()) return result;

        final Map<String, Integer> columnIndexMap = parserUtils.getHeaders(lines.get(0));
        final ColumnIndexes columnIndexes = ColumnIndexes.from(columnIndexMap, COLUMN_NAME_TEAM, COLUMN_NAME_GOALS, COLUMN_NAME_GOALS_ALLOWED);

        for (int i = 1; i < lines.size(); i++) {
            final String line = lines.get(i).trim();
            final String[] tokens = parserUtils.getTokens(line, columnIndexes);
            final String[] selectedTokens = parserUtils.selectToken(tokens, columnIndexes);
            final FootballDataRecord footballDataRecord = recordFactory.createFromTokens(selectedTokens);
            result.add(footballDataRecord);
        }
        return result;
    }
}
