package com.stackroute.MovieApp.controller;

//create unit test cases for all the endpoints under MovieListController class
//use webmvctest annotation

import com.stackroute.MovieApp.model.Movie;
import com.stackroute.MovieApp.repository.MovieListRepository;
import com.stackroute.MovieApp.service.MovieListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MovieListControllerTest {

    @Mock
    private MovieListService movieListService;

    @Mock
    private MovieListRepository movieListRepository;

    @InjectMocks
    private MovieListController movieListController;

    @Mock
    private Movie movie;

//    Add BeforeEach method to initialize the movie object

    @BeforeEach
    public void setUp() {
        movie = new Movie();
        movie.setId("1");
        movie.setTitle("The Shawshank Redemption");
        movie.setYear(1994);
        movie.setImage("https://m.media-amazon.com/images/I/51S5j5lG6FL._AC_SY679_.jpg");
    }

//    Add test case to test the saveMovie method

        @Test
        public void saveMovieTest() throws Exception {
            when(movieListService.saveMovie(movie)).thenReturn(movie);
            MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(movieListController).build();
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourite")
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"id\": \"1\",\n" +
                            "    \"title\": \"The Shawshank Redemption\",\n" +
                            "    \"year\": 1994,\n" +
                            "    \"image\": \"https://m.media-amazon.com/images/I/51S5j5lG6FL._AC_SY679_.jpg\"\n" +
                            "}"))
                    .andExpect(status().isOk());
        }

//    Add test case to test the getAllMovies method

        @Test
        public void getAllMoviesTest() throws Exception {
            List<Movie> movieList = new ArrayList<>();
            movieList.add(movie);
            when(movieListService.getAllMoviesFromAPI()).thenReturn(movieList);
            MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(movieListController).build();
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Shawshank Redemption"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].year").value(1994))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].image").value("https://m.media-amazon.com/images/I/51S5j5lG6FL._AC_SY679_.jpg"));
        }

//    Add test case to test the getFavouriteMovies method

        @Test
        public void getFavouriteMoviesTest() throws Exception {
            List<Movie> movieList = new ArrayList<>();
            movieList.add(movie);
            when(movieListService.getFavoriteList()).thenReturn(movieList);
            MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(movieListController).build();
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourite"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Shawshank Redemption"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].year").value(1994))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].image").value("https://m.media-amazon.com/images/I/51S5j5lG6FL._AC_SY679_.jpg"));
        }

//    Add test case to test the deleteMovie method

            @Test
            public void deleteMovieTest() throws Exception {
                when(movieListService.deleteMovie("1")).thenReturn(true);
                MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(movieListController).build();
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/1"))
                        .andExpect(status().isOk());
            }

}
