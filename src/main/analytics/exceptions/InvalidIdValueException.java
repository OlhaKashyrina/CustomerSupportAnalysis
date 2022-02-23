package main.analytics.exceptions;

/**
 * Custom exception class. Is thrown when invalid ID is being assigned in line parameters.
 */
public class InvalidIdValueException extends Exception{

    public InvalidIdValueException(String errorMessage) {
        super(errorMessage);
    }
}
