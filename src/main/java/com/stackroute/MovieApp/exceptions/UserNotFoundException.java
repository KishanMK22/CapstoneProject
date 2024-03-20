package com.stackroute.MovieApp.exceptions;

//create a class UserNotFoundException which extends Exception

/**
 * The UserNotFoundException class is a custom exception class in the application.
 * It extends the Exception class.
 * This exception is thrown when a user is not found in the database.
 */
public class UserNotFoundException extends Exception {
    /**
     * The message of the exception.
     */
    private String message;

    /**
     * Constructor for the UserNotFoundException.
     * @param message The message of the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
