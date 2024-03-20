package com.stackroute.MovieApp.controller;

//create unit test cases for all the endpoints under UserController class
//use webmvctest annotation

import com.stackroute.MovieApp.model.User;
import com.stackroute.MovieApp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
    }

    @Test
    public void saveUserTest() throws Exception {
        when(userService.saveUser(user)).thenReturn(user);
        MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"email\": \" \",\n" +
                        "    \"password\": \" \"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void validateUserTest() throws Exception {
        when(userService.validateUser(" ", " ")).thenReturn(true);
        MockMvc mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login?email= &password= "))
                .andExpect(status().isOk());
    }

    @Test
    public void generateTokenTest() {
        String token = Jwts.builder().setSubject(" ").setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        assert (token != null);
    }


}

