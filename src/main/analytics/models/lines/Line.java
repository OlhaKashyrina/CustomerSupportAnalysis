package main.analytics.models.lines;

import main.analytics.models.parameters.Question;
import main.analytics.models.parameters.ResponseType;
import main.analytics.models.parameters.Service;

/**
 * An abstract class that represents a line of input.
 * <p>
 * Extended by {@link DataLine} and {@link QueryLine}.
 */
public abstract class Line {

    /**
     * The {@link Service} that the client support was contacted about.
     */
    private Service service;
    /**
     * The type of {@link Question} that has been discussed.
     */
    private Question question;
    /**
     * The {@link ResponseType} - possible variations ‘P’ (first answer) or ‘N’ (next answer)
     */
    private ResponseType response_type;

    /**
     * Class constructor specifying service, question and response type.
     * @param service
     * @param question
     * @param response_type
     */
    public Line(Service service, Question question, ResponseType response_type) {
        this.service = service;
        this.question = question;
        this.response_type = response_type;
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

    @Override
    public String toString() {
        return "Line{" +
                "service=" + service +
                ", question=" + question +
                ", response_type=" + response_type +
                '}';
    }

}
