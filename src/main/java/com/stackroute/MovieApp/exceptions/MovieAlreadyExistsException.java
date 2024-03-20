package com.stackroute.MovieApp.exceptions;

//create a class MovieAlreadyExistsException which extends Exception

/**
 * The MovieAlreadyExistsException class is a custom exception class in the application.
 * It extends the Exception class.
 * This exception is thrown when a movie already exists in the database.
 */
public class MovieAlreadyExistsException extends Exception {
    /**
     * The message of the exception.
     */
    private String message;

    /**
     * Constructor for the MovieAlreadyExistsException.
     * @param message The message of the exception.
     */
    public MovieAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}