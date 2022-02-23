package main.analytics.exceptions;

/**
 * Custom exception class. Is thrown when line does not match any regular expression.
 */
public class InvalidLineException extends Exception {

    public InvalidLineException(String errorMessage) {
        super(errorMessage);
    }
}