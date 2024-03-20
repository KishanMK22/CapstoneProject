package com.stackroute.MovieApp.model;

//Create a model class to be saved in mongodb with the name of User with the following fields:
//userId, name, email, password
//Use lombok annotation to generate getters and setters
//Use @Id annotation to set userId as primary key
//create AllArgsConstructor and NoArgsConstructor
//create java documentation for the class

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The User class is an entity that represents a user in the application.
 * It is annotated with @Document to indicate that it is a MongoDB document.
 * It uses Lombok annotations for boilerplate code reduction.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    /**
     * The userId of the user. It is annotated with @Id to indicate that it is the primary key.
     */
    @Id
    private String userId;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    @Schema(format = "password")
    private String password;
}