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

/**
 * A class that contains static methods for parsing input string and turning them into DataLine and QueryLine objects
 */
public class LineParser {

    /**
     * A constant String that contains regular expression for input data lines validation
     */
    public static final String regexC = "^C ([1-9]|10)(\\.[1-3])? ([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)? " +
            "[PN] ((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})) \\d+$";
    /**
     * A constant String that contains regular expression for input query lines validation
     */
    public static final String regexD = "^D (\\*|([1-9]|10)(\\.[1-3])?) (\\*|([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)?) " +
            "[PN] ((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2}))(-((3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})))?$";

    /**
     * Method that parses one line of input into {@link DataLine} object or {@link QueryLine} object.
     * If line is invalid and cannot be parsed, throws an exception.
     * @param inputLine                  a String that has to be parsed
     * @return                           returns a Line object. If inputLine starts with C, the object is an instance of DataLine.
     *                                   If inputLine starts with D, the object is an instance of QueryLine.
     * @throws InvalidLineException      throws this exception if inputLine does not match any regular expression
     * @throws InvalidIdValueException   throws this exception if any ID in Service or Question object is invalid
     */
    public static Line parse(String inputLine) throws InvalidLineException, InvalidIdValueException {

        if(!(inputLine.matches(regexC) || inputLine.matches(regexD)))
            throw new InvalidLineException("The line \"" + inputLine + "\" is invalid, it will be ignored.");

        Line line;
        String[] values = inputLine.split("\\s");

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

    /**
     * Method that parses the second word in the line and creates a Service object
     * @param idsLine                    a String that is the second object in the list of Strings parsed from an input line
     * @return                           returns Service object
     * @throws InvalidIdValueException   throws this exception if any ID in Service object is invalid
     */
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

    /**
     * Method that parses the third word in the line and creates a Question object
     * @param idsLine                     a String that is the third object in the list of Strings parsed from an input line
     * @return                            returns Question object
     * @throws InvalidIdValueException    throws this exception if any ID in Question object is invalid
     */
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

    /**
     * Method that parses a string with a single date (i.e. 12.12.2012)
     * @param date    a String that contains a single date in format dd.MM.yyyy
     * @return        returns Date object that was parsed from the String
     */
    private static Date extractSingleDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that parses a string with a time period (i.e. 01.12.2012-12.12.2012)
     * @param date    a String that contains two dates in format dd.MM.yyyy-dd.MM.yyyy
     * @return        returns an array of two Date objects that were parsed from the String
     */
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
