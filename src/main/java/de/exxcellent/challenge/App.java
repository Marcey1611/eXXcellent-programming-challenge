package de.exxcellent.challenge;

import de.exxcellent.challenge.core.ApplicationRunner;
import de.exxcellent.challenge.core.ApplicationRunnerFactory;
import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.core.ArgsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(final String... args) {

        ArgsValidator.validate(args);

        final String mode = args[0];
        final String fileName = args[1];

        LOGGER.info("Application started with mode '{}'.", mode);

        try {
            final ApplicationRunner<? extends DataRecord> runner = ApplicationRunnerFactory.createRunner(mode);
            LOGGER.info("Starting process for file '{}'.", fileName);
            final String result = runner.run(fileName);

            if ("--weather".equals(mode)) {
                LOGGER.info("Weather analysis result: {}", result);
                System.out.printf("Day with smallest temperature spread : %s%n", result);
            } else {
                LOGGER.info("Football analysis result: {}", result);
                System.out.printf("Team with smallest goal spread       : %s%n", result);
            }

        } catch (IllegalArgumentException e) {
            LOGGER.error("Unknown mode received: '{}'", mode);
            System.out.println("Unknown mode!");
        }
    }
}

/* Future improvements:
 * - Add more detailed error handling and logging.
 * - Consider using a dependency injection framework for better testability and maintainability. (z.b. Spring)
 */