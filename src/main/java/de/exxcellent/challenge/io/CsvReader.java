package de.exxcellent.challenge.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvReader is responsible for reading CSV files.
 * It provides a method to read the contents of a file and return it as a list of strings.
 */
public class CsvReader {

    private static final String FILE_NAME_EXTENSION = "de/exxcellent/challenge/%s";

    /**
     * Reads the contents of a CSV file and returns it as a list of strings.
     *
     * @param fileName the name of the file to read.
     * @return a list of strings representing the lines in the file.
     * @throws RuntimeException if there is an error reading the file.
     */
    public List<String> readFile(final String fileName) {
        final List<String> lines;
        final String extendedFileName = String.format(FILE_NAME_EXTENSION, fileName);

        try {

            final URL resource = getClass().getClassLoader().getResource(extendedFileName);
            assert resource != null;
            final String filePath = Paths.get(resource.toURI()).toString();
            lines = Files.readAllLines(Path.of(filePath));
        } catch (final URISyntaxException | IOException | NullPointerException exception) {
            //TODO: Implement custom error handling
            throw new RuntimeException("Error while reading the file: " + fileName, exception);
        }

        if (lines.isEmpty()) {
            throw new RuntimeException("File is empty: " + fileName);
        }
        return lines;
    }
}
