package main.analytics.exceptions;

public class InvalidIdValueException extends Exception{

    public InvalidIdValueException(String errorMessage) {
        super(errorMessage);
    }
}
