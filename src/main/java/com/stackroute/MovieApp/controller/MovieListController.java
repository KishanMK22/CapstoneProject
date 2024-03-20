package com.stackroute.MovieApp.controller;

//Create a controller class with the name of MovieListController with below endpoints:
//1. /api/v1/movie - POST - saveMovie
//2. /api/v1/movies - GET - getAllMovies
//3. /api/v1/favourite - GET - getFavouriteMovies
//4. /api/v1/movie/{id} - DELETE - deleteMovie


/**
 * This is the MovieListController class that handles all the HTTP requests related to movies.
 * It has endpoints for saving a movie, getting all movies, getting favourite movies, and deleting a movie.
 *
 * Endpoints:
 * 1. /api/v1/movie - POST - saveMovie
 * 2. /api/v1/movies - GET - getAllMovies
 * 3. /api/v1/favourite - GET - getFavouriteMovies
 * 4. /api/v1/movie/{id} - DELETE - deleteMovie
 */

import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.repository.MovieListRepository;
import com.stackroute.MovieApp.service.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class MovieListController {
    @Autowired
    private MovieListService movieListService;

    @Autowired
    private MovieListRepository movieRepository;

    /**
     * This method is used to save a movie.
     * It throws MovieAlreadyExistsException if the movie already exists in the database.
     * @param movie This is the movie object to be saved.
     * @return Movie This returns the saved movie object.
     */
    @PostMapping("/favourite")
    public Movie saveMovie(@RequestBody Movie movie) throws MovieAlreadyExistsException {
        if (movie == null) {
            throw new IllegalArgumentException("Movie object cannot be null");
        }

        if (movie.getId() == null || movie.getId().isEmpty()) {
            throw new IllegalArgumentException("Movie Id cannot be null or empty");
        }

        // Check if the movie already exists in the database
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieAlreadyExistsException("Movie with Id " + movie.getId() + " already exists");
        }

        try {
            return movieListService.saveMovie(movie);
        } catch (MovieAlreadyExistsException e) {
            // Log the error and rethrow the exception
            // Or return a default value
            throw e;
        }
    }

    /**
     * This method is used to get all movies.
     * @return List<Movie> This returns a list of all movies.
     */
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieListService.getAllMoviesFromAPI();
    }

    /**
     * This method is used to get favourite movies.
     * @return List<Movie> This returns a list of favourite movies.
     */
    @GetMapping("/favourite")
    public List<Movie> getFavouriteMovies() {
        return movieListService.getFavoriteList();
    }

    /**
     * This method is used to delete a movie by id.
     * It throws MovieNotFoundException if the movie with the given id is not found.
     * @param id This is the id of the movie to be deleted.
     * @return String This returns a success message if the movie is deleted successfully.
     */
    @DeleteMapping("/movie/{id}")
    public String deleteMovie(@PathVariable String id) throws MovieNotFoundException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        if (movieListService.deleteMovie(id)) {
            return "Deleted Successfully";
        } else {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
    }

}