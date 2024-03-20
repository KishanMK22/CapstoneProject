package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.exceptions.UserAlreadyExistsException;
import com.stackroute.MovieApp.exceptions.UserNotFoundException;
import com.stackroute.MovieApp.model.User;
import com.stackroute.MovieApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserId("1");
        user.setEmail("test@test.com");
        user.setPassword("password");
    }

    @Test
    public void saveUserReturnsUserWhenUserDoesNotExist() throws UserAlreadyExistsException {
        when(userRepository.existsById(user.getUserId())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void saveUserThrowsExceptionWhenUserAlreadyExists() throws UserAlreadyExistsException {
        when(userRepository.existsById(user.getUserId())).thenReturn(true);
        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(user));
    }

    @Test
    public void validateUserReturnsTrueWhenUserIsValid() throws UserNotFoundException {
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        boolean isValid = userService.validateUser(user.getEmail(), user.getPassword());
        assertTrue(isValid);
    }

    @Test
    public void validateUserThrowsExceptionWhenUserIsInvalid() throws UserNotFoundException {
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.validateUser(user.getEmail(), user.getPassword()));
    }
}