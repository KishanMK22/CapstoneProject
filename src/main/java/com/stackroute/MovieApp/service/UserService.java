package com.stackroute.MovieApp.service;

//create interface class with saveuser & validateuser methods

import com.stackroute.MovieApp.exceptions.UserAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.UserNotFoundException;
import com.stackroute.MovieApp.model.User;

/**
 * The UserService interface provides the contract for the User service.
 * It defines methods to save a user and validate a user's credentials.
 * It throws custom exceptions for specific error scenarios.
 */
public interface UserService {
    /**
     * Saves a user to the database.
     * @param user The user to be saved.
     * @throws UserAlreadyExistsException if the user already exists in the database.
     */
    public User saveUser(User user) throws UserAlreadyExistsException;

    /**
     * Validates a user's credentials.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A boolean indicating the success of the operation.
     * @throws UserNotFoundException if the user is not found in the database.
     */
    public boolean validateUser(String email, String password) throws UserNotFoundException;
}
