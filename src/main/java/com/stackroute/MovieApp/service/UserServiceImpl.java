package com.stackroute.MovieApp.service;

//create implementation class for UserService
//add save user implementation to save the user to mongodb
//add validate user implementation to validate the user from mongodb

import com.stackroute.MovieApp.exceptions.UserAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.UserNotFoundException;
import com.stackroute.MovieApp.model.User;
import com.stackroute.MovieApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The UserServiceImpl class provides the implementation for the User service.
 * It defines methods to save a user and validate a user's credentials.
 * It throws custom exceptions for specific error scenarios.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * The repository to interact with the MongoDB database.
     */
    private UserRepository userRepository;

    /**
     * Constructor for the UserServiceImpl class.
     * @param userRepository The UserRepository to interact with the MongoDB database.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user to the database.
     * @param user The user to be saved.
     * @throws UserAlreadyExistsException if the user already exists in the database.
     */
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException{
        if (userRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Validates a user's credentials.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A boolean indicating the success of the operation.
     * @throws UserNotFoundException if the user is not found in the database.
     */
    @Override
    public boolean validateUser(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return true;
    }
}
