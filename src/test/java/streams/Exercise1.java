package streams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise1 {

    @Test
    void getStatisticFromText() throws URISyntaxException {

        Path path = Path.of(Exercise1.class.getResource("/example6_data.txt").toURI());


        try (Stream<String> lines = Files.lines(path)) {
            IntSummaryStatistics intSummaryStatistics = lines.flatMap(string -> Arrays.stream(string.split("[\\s+-]")))
                    .map(string -> string.replaceAll("[^а-яёА-ЯЁ]+", ""))
                    .map(String::toLowerCase)
                    .distinct()
                    .mapToInt(String::length)
                    .summaryStatistics();
            System.out.println(intSummaryStatistics.getCount());
            System.out.println(intSummaryStatistics.getAverage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
