package com.stackroute.MovieApp.exceptions;

//create a class UserAlreadyExistsException which extends Exception

/**
 * The UserAlreadyExistsException class is a custom exception class in the application.
 * It extends the Exception class.
 * This exception is thrown when a user is already present in the database.
 */
public class UserAlreadyExistsException extends Exception {
    /**
     * The message of the exception.
     */
    private String message;

    /**
     * Constructor for the UserAlreadyExistsException.
     * @param message The message of the exception.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
