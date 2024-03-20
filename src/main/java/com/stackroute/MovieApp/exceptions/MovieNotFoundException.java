package com.stackroute.MovieApp.exceptions;

//create a class MovieNotFoundException which extends Exception

/**
 * The MovieNotFoundException class is a custom exception class in the application.
 * It extends the Exception class.
 * This exception is thrown when a movie is not found in the database.
 */
public class MovieNotFoundException extends Exception {
    /**
     * The message of the exception.
     */
    private String message;

    /**
     * Constructor for the MovieNotFoundException.
     * @param message The message of the exception.
     */
    public MovieNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
