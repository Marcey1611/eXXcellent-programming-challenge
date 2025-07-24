package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.factory.FootballRecordFactory;
import de.exxcellent.challenge.model.FootballDataRecord;
import de.exxcellent.challenge.model.WeatherDataRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FootballDataParser implements DataParser<FootballDataRecord> {

    private final ParserUtils parserUtils;
    private final FootballRecordFactory recordFactory;

    private static final String COLUMN_NAME_TEAM = "Team";
    private static final String COLUMN_NAME_GOALS = "Goals";
    private static final String COLUMN_NAME_GOALS_ALLOWED = "Goals Allowed";

    public FootballDataParser(ParserUtils parserUtils, FootballRecordFactory recordFactory) {
        this.parserUtils = parserUtils;
        this.recordFactory = recordFactory;
    }

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
