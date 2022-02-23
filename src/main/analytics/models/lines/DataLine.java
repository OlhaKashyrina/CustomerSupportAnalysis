package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.util.Date;

public class DataLine extends Line {
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
