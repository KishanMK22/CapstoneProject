package com.stackroute.MovieApp.repository;

//Create a repository class with the name of UserRepository with CRUD operations using mongodb.
//create method to find user by email & password

import com.stackroute.MovieApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository interface is a Spring Data JPA repository for User entities.
 * It extends MongoRepository which provides methods like save(), findOne(), findAll(), count(), delete() etc.
 * We can also define our custom finder methods in it.
 * It is annotated with @Repository to indicate that it's a Repository class.
 * It has a custom finder method findByEmailAndPassword() to find a user by their email and password.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Custom finder method to find a user by their email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user if found, null otherwise.
     */
    User findByEmailAndPassword(String email, String password);
}
