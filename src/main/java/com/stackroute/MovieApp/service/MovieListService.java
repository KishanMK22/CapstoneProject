package com.stackroute.MovieApp.service;

//Create interface class with method to get all the movies from the API,save the movie to favorite list, get the movies from favorite list, delete the movie from the favorite list
// throw MovieNotFoundException if id is not found
// throw MovieAlreadyExistsException if id is already exists

import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.model.MoviesList;

import java.util.List;

/**
 * The MovieListService interface provides the contract for the Movie service.
 * It defines methods to interact with the Movie API and perform CRUD operations on the favorite list.
 * It throws custom exceptions for specific error scenarios.
 */
public interface MovieListService {
    /**
     * Fetches all movies from the API.
     * @return A MoviesList object containing all movies from the API.
     */
    List<Movie> getAllMoviesFromAPI();

    /**
     * Saves a movie to the favorite list.
     * @param movie The movie to be saved.
     * @return The saved movie.
     * @throws MovieAlreadyExistsException if the movie already exists in the favorite list.
     */
    Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

    /**
     * Fetches all movies from the favorite list.
     * @return A list of movies from the favorite list.
     */
    List<Movie> getFavoriteList();

    /**
     * Deletes a movie from the favorite list.
     * @param id The id of the movie to be deleted.
     * @return A boolean indicating the success of the operation.
     * @throws MovieNotFoundException if the movie is not found in the favorite list.
     */
    Boolean deleteMovie(String id) throws MovieNotFoundException;
}
