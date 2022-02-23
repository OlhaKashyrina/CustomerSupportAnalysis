package main.analytics.exceptions;

/**
 * Custom exception class. Is thrown when input file is empty or data is invalid or none of the data lines meet the query criteria.
 */
public class NoDataToAnalyseException extends Exception{

    public NoDataToAnalyseException(String errorMessage) {
        super(errorMessage);
    }
}