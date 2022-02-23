package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.util.Date;

//Instances of this class contain data stored in input lines that start with C
public class DataLine extends Line {

    //In addition to Line fields contains the waiting time
    private Integer time;

    public DataLine(Service service, Question question, ResponseType response_type, Date date, Integer time) {
        super(service, question, response_type, date);
        this.time = time;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "time=" + time +
                "} " + super.toString();
    }
}
