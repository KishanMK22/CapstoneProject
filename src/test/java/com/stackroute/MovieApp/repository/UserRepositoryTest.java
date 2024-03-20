package com.stackroute.MovieApp.repository;

import com.stackroute.MovieApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserId("1");
        user.setEmail("test@test.com");
        user.setPassword("password");
    }

    @Test
    public void findByEmailAndPasswordReturnsUserWhenUserExists() {
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        User foundUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        assertEquals(user, foundUser);
    }

    @Test
    public void findByEmailAndPasswordReturnsNullWhenUserDoesNotExist() {
        when(userRepository.findByEmailAndPassword("wrong@test.com", "wrongpassword")).thenReturn(null);
        User foundUser = userRepository.findByEmailAndPassword("wrong@test.com", "wrongpassword");
        assertNull(foundUser);
    }

    @Test
    public void saveReturnsUserWhenSavingUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);
        assertEquals(user, savedUser);
    }
}
