package main.analytics.exceptions;

public class NoDataToAnalyseException extends Exception{

    public NoDataToAnalyseException(String errorMessage) {
        super(errorMessage);
    }
}