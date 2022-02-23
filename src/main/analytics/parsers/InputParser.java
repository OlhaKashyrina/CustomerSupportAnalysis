package main.analytics.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {

    public static List<String> parseFile(String pathToFile) throws IOException {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(pathToFile))) {
            result = lines.collect(Collectors.toList());
        }
        catch (IOException e) {
            System.out.println("Could not parse the file: " + e);
            throw e;
        }
        return result;
    }

}
