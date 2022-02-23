package main.analytics;

import main.analytics.exceptions.NoDataToAnalyseException;
import main.analytics.parsers.InputParser;
import main.analytics.parsers.OutputGenerator;
import java.io.IOException;
import java.util.Scanner;

public class Analytics {

    public static final String TRY_AGAIN = "Please, try again.";
    public static final String WELCOME = "Welcome to Customer Support Analytics!";
    public static final String INSTRUCTION = "Enter the path to a file with input data or enter \"exit\" to quit:";
    public static final String EXIT = "exit";

    public static void main(String[] args) {
        System.out.println(WELCOME);
        boolean running = true;
        while (running)
        {
            System.out.println(INSTRUCTION);
            String path = new Scanner(System.in).nextLine();
            if (path.equals(EXIT))
                running = false;
            else
                try {
                    System.out.println("Done! File with the result can be found here: " + analyseData(path));
                } catch (IOException e) {
                    System.out.println(TRY_AGAIN);
                } catch (NoDataToAnalyseException e) {
                    System.out.println(e + TRY_AGAIN);
                }
        }
    }

    private static String analyseData(String path) throws IOException, NoDataToAnalyseException {
        OutputGenerator og = new OutputGenerator();
        return og.writeReport(InputParser.parseFile(path), path);
    }
}
