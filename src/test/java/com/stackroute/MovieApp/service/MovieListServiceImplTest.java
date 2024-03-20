package com.stackroute.MovieApp.service;

//create unit test cases for all the methods in MovieListServiceImpl class

import com.stackroute.MovieApp.exceptions.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.repository.MovieListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieListServiceImplTest {

    @Mock
    private MovieListRepository movieListRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MovieListServiceImpl movieListService;

    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie();
        movie.setId("1");
        movie.setTitle("Test Movie");
    }

    @Test
    public void saveMovieReturnsMovieWhenMovieDoesNotExist() throws MovieAlreadyExistsException {
        when(movieListRepository.existsById(movie.getId())).thenReturn(false);
        when(movieListRepository.save(movie)).thenReturn(movie);
        Movie savedMovie = movieListService.saveMovie(movie);
        assertEquals(movie, savedMovie);
    }

    @Test
    public void saveMovieThrowsExceptionWhenMovieAlreadyExists() throws MovieAlreadyExistsException {
        when(movieListRepository.existsById(movie.getId())).thenReturn(true);
        assertThrows(MovieAlreadyExistsException.class, () -> movieListService.saveMovie(movie));
    }

    @Test
    public void getFavoriteListReturnsListOfMovies() {
        when(movieListRepository.findAll()).thenReturn(Arrays.asList(movie));
        List<Movie> movies = movieListService.getFavoriteList();
        assertEquals(1, movies.size());
        assertEquals(movie, movies.get(0));
    }

    @Test
    public void deleteMovieReturnsTrueWhenMovieExists() throws MovieNotFoundException {
        when(movieListRepository.existsById(movie.getId())).thenReturn(true);
        Boolean result = movieListService.deleteMovie(movie.getId());
        assertTrue(result);
    }

    @Test
    public void deleteMovieThrowsExceptionWhenMovieDoesNotExist() throws MovieNotFoundException {
        when(movieListRepository.existsById(movie.getId())).thenReturn(false);
        assertThrows(MovieNotFoundException.class, () -> movieListService.deleteMovie(movie.getId()));
    }
}