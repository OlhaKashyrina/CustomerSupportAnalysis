package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.util.ArrayList;
import java.util.Date;

//Instances of this class contain data stored in input lines that start with D
public class QueryLine extends Line {

    //In addition to Line fields contains date_to. If not specified, is assigned current Date
    private Date date_to;

    public QueryLine(Service service, Question question, ResponseType response_type, Date date) {
        super(service, question, response_type, date);
        date_to = new Date();
    }

    public QueryLine(Service service, Question question, ResponseType response_type, Date date, Date date_to) {
        super(service, question, response_type, date);
        this.date_to = date_to;
    }

    //Method returns true if dataLine needs to be analysed in current query line
    public boolean isValid(DataLine dataLine)
    {
        return this.getService().isValidForDataLine(dataLine.getService()) &&
                this.getQuestion().isValidForDataLine(dataLine.getQuestion()) &&
                this.getResponse_type() == dataLine.getResponse_type() &&
                this.getDate().before(dataLine.getDate()) &&
                this.getDate_to().after(dataLine.getDate());
    }

    //Method returns the average waiting time for a list of data lines
    //If no line in the list matches the criteria, returns "-"
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

    @Override
    public String toString() {
        return "QueryLine{" +
                "date_to=" + date_to +
                "} " + super.toString();
    }
}
