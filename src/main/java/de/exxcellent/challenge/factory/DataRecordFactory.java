package de.exxcellent.challenge.factory;

import de.exxcellent.challenge.model.DataRecord;

/**
 * Factory interface for creating DataRecord instances from an array of tokens.
 *
 * @param <T> the type of DataRecord to be created
 */
public interface DataRecordFactory<T extends DataRecord> {
    T createFromTokens(final String[] tokens);
}
