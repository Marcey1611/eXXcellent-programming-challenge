package de.exxcellent.challenge.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * CsvReader is responsible for reading CSV files.
 * It provides a method to read the contents of a file and return it as a list of strings.
 */
public class CsvReader {

    /**
     * Reads the contents of a CSV file and returns it as a list of strings.
     *
     * @param filePath the path to the CSV file.
     * @return a list of strings representing the lines in the file.
     * @throws RuntimeException if there is an error reading the file.
     */
    public List<String> readFile(final String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (final IOException exception) {
            throw new RuntimeException("Fehler beim Lesen der Datei: " + filePath, exception);
        }
    }
}
