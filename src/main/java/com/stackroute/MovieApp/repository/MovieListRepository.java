package com.stackroute.MovieApp.repository;

//Create a repository class with CRUD operations using mongodb.

import com.stackroute.MovieApp.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The MovieListRepository interface is a Spring Data JPA repository for Movie entities.
 * It extends MongoRepository which provides methods like save(), findOne(), findAll(), count(), delete() etc.
 * We can also define our custom finder methods in it.
 * It is annotated with @Repository to indicate that it's a Repository class.
 */
@Repository
public interface MovieListRepository extends MongoRepository<Movie, String> {

}
