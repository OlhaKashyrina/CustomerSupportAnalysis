package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents query lines of input. Extends {@link Line}.
 * <p>
 * Instances of this class contain data stored in input lines that start with D. The main difference between this class
 * and {@link DataLine} is that QueryLine does not contain an Integer value which represents waiting time in minutes.
 * Instead, includes field date_from and optional field date_to to specify period of time for data analysis.
 */
public class QueryLine extends Line {

    /**
     * Lower date limit when the response was given. Format dd.MM.yyyy.
     */
    private Date date_from;
    /**
     * Upper date limit when the response was given. If not specified, is assigned current Date. Format dd.MM.yyyy.
     */
    private Date date_to;

    /**
     * Class constructor specifying every possible field except date_to which will be automatically generated.
     * Used to create QueryLine instances from validated input lines.
     * @param service
     * @param question
     * @param response_type
     * @param date_from
     */
    public QueryLine(Service service, Question question, ResponseType response_type, Date date_from) {
        super(service, question, response_type);
        this.date_from = date_from;
        date_to = new Date();
    }

    /**
     * Class constructor specifying every possible field.
     * Used to create QueryLine instances from validated input lines.
     * @param service
     * @param question
     * @param response_type
     * @param date_from
     * @param date_to
     */
    public QueryLine(Service service, Question question, ResponseType response_type, Date date_from, Date date_to) {
        super(service, question, response_type);
        this.date_from = date_from;
        this.date_to = date_to;
    }

    /**
     * Method for checking if a data line needs to be analysed in current query line.
     * @param dataLine data line that has to be checked on matching criteria
     * @return         returns true if data line meets the criteria of current query line
     */
    public boolean isValid(DataLine dataLine)
    {
        return this.getService().isValidForDataLine(dataLine.getService()) &&
                this.getQuestion().isValidForDataLine(dataLine.getQuestion()) &&
                this.getResponse_type() == dataLine.getResponse_type() &&
                this.getDate_from().before(dataLine.getDate()) &&
                this.getDate_to().after(dataLine.getDate());
    }

    /**
     * Method checks data lines in the list for compliance with the criteria and
     * calculates the average waiting time for the ones that met the criteria.
     * @param dataLines a list of all data lines written above the current query line.
     * @return returns the average waiting time as String. If none of the data lines
     * met the criteria, returns "-".
     */
    public String calculateWaitingTime(ArrayList<DataLine> dataLines) {

        int counter = 0;
        int sum = 0;
        for (DataLine dl : dataLines)
            if(isValid(dl)){
                sum += dl.getTime();
                counter++;
            }
        return counter == 0 ? "-" : String.valueOf(sum / counter);
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    @Override
    public String toString() {
        return "QueryLine{" +
                "date_from=" + date_from +
                ", date_to=" + date_to +
                "} " + super.toString();
    }
}
