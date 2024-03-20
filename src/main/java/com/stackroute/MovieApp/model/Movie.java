package com.stackroute.MovieApp.model;

//Create movie class entity to save in mongodb for below json object
//{
//    "rank": 1,
//    "title": "The Shawshank Redemption",
//    "thumbnail": "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY67_CR0,0,45,67_AL_.jpg",
//    "rating": "9.3",
//    "id": "top1",
//    "year": 1994,
//    "image": "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_QL75_UX380_CR0,1,380,562_.jpg",
//    "big_image": "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@",
//    "description": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//    "trailer": "https://www.youtube.com/watch?v=NmzuHjWmXOc",
//    "trailer_embed_link": "https://www.youtube.com/embed/NmzuHjWmXOc",
//    "trailer_youtube_id": "NmzuHjWmXOc",
//    "genre": [
//        "Drama"
//    ],
//    "director": [
//        "Frank Darabont"
//    ],
//    "writers": [
//        "Stephen King(based on the short novel \"Rita Hayworth and the Shawshank Redemption\" by)",
//        "Frank Darabont(screenplay by)"
//    ],
//    "imdbid": "tt0111161",
//    "imdb_link": "https://www.imdb.com/title/tt0111161"
//}
//create gettter & setter using lombok data annotation
//create noargs and allargs constructor using lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The Movie class is an entity that represents a movie in the application.
 * It is annotated with @Document to indicate that it is a MongoDB document.
 * It uses Lombok annotations for boilerplate code reduction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Movie {
    /**
     * The id of the movie. It is annotated with @Id to indicate that it is the primary key.
     */
    @Id
    private String id;

    /**
     * The rank of the movie.
     */
    private int rank;

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The URL of the movie's thumbnail.
     */
    private String thumbnail;

    /**
     * The rating of the movie.
     */
    private String rating;

    /**
     * The year the movie was released.
     */
    private int year;

    /**
     * The URL of the movie's image.
     */
    private String image;

    /**
     * The URL of the movie's big image.
     */
    private String big_image;

    /**
     * The description of the movie.
     */
    private String description;

    /**
     * The URL of the movie's trailer.
     */
    private String trailer;

    /**
     * The embed link of the movie's trailer.
     */
    private String trailer_embed_link;

    /**
     * The YouTube ID of the movie's trailer.
     */
    private String trailer_youtube_id;

    /**
     * The genres of the movie. It is a list of strings.
     */
    private List<String> genre;

    /**
     * The directors of the movie. It is a list of strings.
     */
    private List<String> director;

    /**
     * The writers of the movie. It is a list of strings.
     */
    private List<String> writers;

    /**
     * The IMDb ID of the movie.
     */
    private String imdbid;

    /**
     * The IMDb link of the movie.
     */
    private String imdb_link;
}