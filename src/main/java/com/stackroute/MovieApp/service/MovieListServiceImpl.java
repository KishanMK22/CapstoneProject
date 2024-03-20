package com.stackroute.MovieApp.service;

//create a service class with the name of MovieListServiceImpl with implementation for all the methods under MovieListService interface with respective implementation to the repository
//Add resttemplate autowired to get the movie list from the rapidapi using the host and key from application.properties
//create variable API_URL to store the url of the rapidapi from application.properties using @value annotation
//create variable API_KEY to store the key of the rapidapi from application.properties using @value annotation

import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.repository.MovieListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The MovieListServiceImpl class provides the implementation for the Movie service.
 * It defines methods to interact with the Movie API and perform CRUD operations on the favorite list.
 * It throws custom exceptions for specific error scenarios.
 */
@Service
public class MovieListServiceImpl implements MovieListService {

    /**
     * The URL of the RapidAPI.
     */
    @Value("${rapidapi.url}")
    private String API_URL;

    /**
     * The key of the RapidAPI.
     */
    @Value("${rapidapi.key}")
    private String API_KEY;

    /**
     * The repository to interact with the MongoDB database.
     */
    @Autowired
    private MovieListRepository movieRepository;


    /**
     * The RestTemplate to send HTTP requests.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Fetches all movies from the API.
     * @return A MoviesList object containing all movies from the API.
     */
    @Override
    public List<Movie> getAllMoviesFromAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<List<Movie>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Movie>>() {}
        );
        return response.getBody();
    }

    /**
     * Saves a movie to the favorite list.
     * @param movie The movie to be saved.
     * @return The saved movie.
     * @throws MovieAlreadyExistsException if the movie already exists in the favorite list.
     */
    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieAlreadyExistsException("Movie with id " + movie.getId() + " already exists");
        }
        return movieRepository.save(movie);
    }

    /**
     * Fetches all movies from the favorite list.
     * @return A list of movies from the favorite list.
     */
    @Override
    public List<Movie> getFavoriteList() {
        return movieRepository.findAll();
    }

    /**
     * Deletes a movie from the favorite list.
     * @param id The id of the movie to be deleted.
     * @return A boolean indicating the success of the operation.
     * @throws MovieNotFoundException if the movie is not found in the favorite list.
     */
    @Override
    public Boolean deleteMovie(String id) throws MovieNotFoundException {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            throw new MovieNotFoundException("Movie with id " + id + " does not exist");
        }
    }
}
