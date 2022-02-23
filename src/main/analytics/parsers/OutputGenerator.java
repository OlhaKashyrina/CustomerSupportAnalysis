package main.analytics.parsers;

import main.analytics.exceptions.InvalidIdValueException;
import main.analytics.exceptions.InvalidLineException;
import main.analytics.exceptions.NoDataToAnalyseException;
import main.analytics.models.lines.DataLine;
import main.analytics.models.lines.Line;
import main.analytics.models.lines.QueryLine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OutputGenerator {


    public List<String> calculateOutput(List<String> allStrings) throws NoDataToAnalyseException {

        if(allStrings.isEmpty()) throw new NoDataToAnalyseException("Your file is empty. ");

        ArrayList<DataLine> dataLines = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        allStrings.remove(0);
        for (String s : allStrings) {
            Line line = null;
            try {
                line = LineParser.parse(s);
            } catch (InvalidLineException e) {
                System.out.println(e);
            } catch (InvalidIdValueException e) {
                e.printStackTrace();
            }
            if(line instanceof DataLine)
                dataLines.add((DataLine) line);
            if(line instanceof QueryLine)
                output.add(((QueryLine) line).calculateWaitingTime(dataLines));
        }
        if(output.isEmpty()) throw new NoDataToAnalyseException("There is no data to analyse. ");
        return output;
    }

    public String writeReport(List<String> allStrings, String path) throws NoDataToAnalyseException {

        String writePath = Paths.get(path).getParent().toString() + "/result.txt";
        try {
            Files.write(Paths.get(writePath), calculateOutput(allStrings), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writePath;
    }
}
