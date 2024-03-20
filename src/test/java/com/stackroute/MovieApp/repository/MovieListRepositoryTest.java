package com.stackroute.MovieApp.repository;

import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.repository.MovieListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieListRepositoryTest {

    @Mock
    private MovieListRepository movieListRepository;

    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie();
        movie.setId("1");
        movie.setTitle("Test Movie");
    }

    @Test
    public void findByIdReturnsMovieWhenMovieExists() {
        when(movieListRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        Optional<Movie> foundMovie = movieListRepository.findById(movie.getId());
        assertTrue(foundMovie.isPresent());
        assertEquals(movie, foundMovie.get());
    }

    @Test
    public void findByIdReturnsEmptyWhenMovieDoesNotExist() {
        when(movieListRepository.findById("2")).thenReturn(Optional.empty());
        Optional<Movie> foundMovie = movieListRepository.findById("2");
        assertFalse(foundMovie.isPresent());
    }

    @Test
    public void saveReturnsMovieWhenSavingMovie() {
        when(movieListRepository.save(movie)).thenReturn(movie);
        Movie savedMovie = movieListRepository.save(movie);
        assertEquals(movie, savedMovie);
    }
}
