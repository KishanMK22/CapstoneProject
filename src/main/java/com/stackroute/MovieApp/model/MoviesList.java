package com.stackroute.MovieApp.model;

//create a class MoviesList with a list of movie object
//Create the above mentioned fields and generate the getter and setter methods using lombok Data annotation.
//Create no argument constructor using lombok NoArgsConstructor annotation.
//Create parameterized constructor using lombok AllArgsConstructor annotation.
//create java documentation for the class
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The MoviesList class is an entity that represents a list of movies in the application.
 * It uses Lombok annotations for boilerplate code reduction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviesList {
    private List<Movie> data;
}
