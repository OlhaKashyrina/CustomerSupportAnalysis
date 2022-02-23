package main.analytics;

import main.analytics.exceptions.NoDataToAnalyseException;
import main.analytics.parsers.InputParser;
import main.analytics.parsers.OutputGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Analytics {

    public static final String tryAgain = "Please, try again.";

    public static void main(String[] args) {
        System.out.println("Welcome to Customer Support Analytics!");
        boolean running = true;
        while (running)
        {
            System.out.println("Enter the path to a file with input data or enter \"exit\" to quit:");
            String path = new Scanner(System.in).nextLine();
            if (path.equals("exit"))
                running = false;
            else
                try {
                    OutputGenerator og = new OutputGenerator();
                    String resultPath = og.writeReport(InputParser.parseFile(path), path);
                    System.out.println("Done! File with the result can be found here: " + resultPath);
                } catch (IOException e) {
                    System.out.println(tryAgain);
                } catch (NoDataToAnalyseException e) {
                    System.out.println(e + tryAgain);
                }
        }
    }
}
