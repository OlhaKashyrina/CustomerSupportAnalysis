package main.analytics.parsers;

import main.analytics.exceptions.InvalidIdValueException;
import main.analytics.exceptions.InvalidLineException;
import main.analytics.models.lines.DataLine;
import main.analytics.models.lines.Line;
import main.analytics.models.lines.QueryLine;
import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//A class that contains methods for parsing input string and turning them into DataLine and QueryLine objects
public class LineParser {

    //Regular expressions for input lines validation
    public static final String regexC = "^C ([1-9]|10)(\\.[1-3])? ([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)? [PN] ((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})) \\d+$";
    public static final String regexD = "^D (\\*|([1-9]|10)(\\.[1-3])?) (\\*|([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)?) [PN] ((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2}))(-((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})))?$";

    public static Line parse(String inputLine) throws InvalidLineException, InvalidIdValueException {

        //Line validation
        if(!(inputLine.matches(regexC) || inputLine.matches(regexD)))
            throw new InvalidLineException("The line \"" + inputLine + "\" is invalid, it will be ignored.");

        Line line;
        String[] values = inputLine.split("\\s");

        //Line entities builder
        switch (values[0]) {
            case "C":
                line = new DataLine(makeService(values[1]), makeQuestion(values[2]), ResponseType.valueOf(values[3]), extractSingleDate(values[4]), Integer.parseInt(values[5]));
                break;
            case "D":
                if(values[4].contains("-"))
                    line = new QueryLine(makeService(values[1]), makeQuestion(values[2]), ResponseType.valueOf(values[3]), extractTwoDates(values[4])[0], extractTwoDates(values[4])[1]);
                else line = new QueryLine(makeService(values[1]), makeQuestion(values[2]), ResponseType.valueOf(values[3]), extractSingleDate(values[4]));
                break;
            default: return null;
        }
        return line;
    }

    //Method parses the second word in the line and creates a Service object
    private static Service makeService(String idsLine) throws InvalidIdValueException {
        Service service = new Service();
        if (idsLine.equals("*"))
            return service;
        if(!idsLine.contains("."))
            service.setService_id(Integer.parseInt(idsLine));
        else {
            String[] ids = idsLine.split("\\.");
            service.setService_id(Integer.parseInt(ids[0]));
            service.setVariation_id(Integer.parseInt(ids[1]));
        }
        return service;
    }

    //Method parses the third word in the line and creates a Question object
    private static Question makeQuestion(String idsLine) throws InvalidIdValueException {
        Question question = new Question();
        if(idsLine.equals("*"))
            return question;
        if(!idsLine.contains("."))
            question.setQuestion_type_id(Integer.parseInt(idsLine));
        else {
            String[] ids = idsLine.split("\\.");
            question.setQuestion_type_id(Integer.parseInt(ids[0]));
            question.setCategory_id(Integer.parseInt(ids[1]));
            if(ids.length > 2)
                question.setSub_category_id(Integer.parseInt(ids[2]));
        }
        return question;
    }

    //Method parses a string with a single date (12.12.2012)
    private static Date extractSingleDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Method parses a string with date_from and date_to (01.12.2012-12.12.2012)
    private static Date[] extractTwoDates(String date) {
        String[] dates = date.split("-");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return new Date[] { formatter.parse(dates[0]), formatter.parse(dates[1]) };
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
