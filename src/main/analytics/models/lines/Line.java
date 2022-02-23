package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

import java.util.Date;

//An abstract class, extended by DataLine and QueryLine
//Represents an entity that contains information stored in every line of input
public abstract class Line {

    private Service service;
    private Question question;

    private ResponseType response_type;
    private Date date;

    Line(){}

    public Line(Service service, Question question, ResponseType response_type, Date date) {
        this.service = service;
        this.question = question;
        this.response_type = response_type;
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ResponseType getResponse_type() {
        return response_type;
    }

    public void setResponse_type(ResponseType response_type) {
        this.response_type = response_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Line{" +
                "service=" + service +
                ", question=" + question +
                ", response_type=" + response_type +
                ", date=" + date +
                '}';
    }

}
