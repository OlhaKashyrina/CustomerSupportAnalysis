package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;
import java.util.Date;

/**
 * A class that represents data lines of input. Extends {@link Line}.
 * <p>
 * Instances of this class contain data stored in input lines that start with C. The main difference between this class
 * and {@link QueryLine} is that DataLine contains an Integer value which represents waiting time in minutes.
 */
public class DataLine extends Line {

    /**
     * Response date. Date format is dd.MM.yyyy, for example, 27.11.2012 (November 27, 2012).
     */
    private Date date;
    /**
     * Waiting time in minutes.
     */
    private Integer time;

    /**
     * Class constructor specifying every possible field. Used to create DataLine instances from validated input lines.
     * @param service
     * @param question
     * @param response_type
     * @param date
     * @param time
     */
    public DataLine(Service service, Question question, ResponseType response_type, Date date, Integer time) {
        super(service, question, response_type);
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                "date=" + date +
                ", time=" + time +
                "} " + super.toString();
    }
}
