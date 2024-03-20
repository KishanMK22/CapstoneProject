package com.stackroute.MovieApp.controller;
//Create a controller class with the name of UserController
import com.stackroute.MovieApp.exceptions.UserAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.UserNotFoundException;
import com.stackroute.MovieApp.model.User;
import com.stackroute.MovieApp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create a method to save the user to H2 database
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Create a method to validate the user from H2 database using getmapping and call the generateToken method if user is validated else throw user not found exception
    @GetMapping("/login")
    public ResponseEntity<?> validateUser(@RequestParam String email, @RequestParam String password) {
        ResponseEntity responseEntity;
        try {
            userService.validateUser(email, password);
            String token = generateToken(email);
            responseEntity = new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    //create method generateToken to generate a token a smiple java function without using any mapping
    //take emailId as argument , using Jwts.builder generate a token and return the token
//    set the token expiry time to 1 hour
    public String generateToken(String emailId) {
        String token = Jwts.builder().setSubject(emailId).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return token;
    }


}
