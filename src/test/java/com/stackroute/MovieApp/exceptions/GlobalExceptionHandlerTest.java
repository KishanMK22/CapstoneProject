package com.stackroute.MovieApp.exceptions;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private MovieNotFoundException movieNotFoundException;

    @Mock
    private MovieAlreadyExistsException movieAlreadyExistsException;

    @Mock
    private UserNotFoundException userNotFoundException;

    @Mock
    private UserAlreadyExistsException userAlreadyExistsException;

    @Mock
    private IllegalArgumentException illegalArgumentException;

    @Mock
    private ServletException servletException;

    @Mock
    private Exception exception;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void handleMovieNotFoundExceptionReturnsNotFoundStatus() {
        when(movieNotFoundException.getMessage()).thenReturn("Movie not found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleMovieNotFoundException(movieNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void handleMovieAlreadyExistsExceptionReturnsConflictStatus() {
        when(movieAlreadyExistsException.getMessage()).thenReturn("Movie already exists");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleMovieAlreadyExistsException(movieAlreadyExistsException);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void handleUserNotFoundExceptionReturnsNotFoundStatus() {
        when(userNotFoundException.getMessage()).thenReturn("User not found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleUserNotFoundException(userNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void handleUserAlreadyExistsExceptionReturnsConflictStatus() {
        when(userAlreadyExistsException.getMessage()).thenReturn("User already exists");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleUserAlreadyExistsException(userAlreadyExistsException);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void handleIllegalArgumentExceptionReturnsBadRequestStatus() {
        when(illegalArgumentException.getMessage()).thenReturn("Illegal argument");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleIllegalArgumentException(illegalArgumentException);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void handleServletExceptionReturnsInternalServerErrorStatus() {
        when(servletException.getMessage()).thenReturn("Servlet exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleServletException(servletException);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void handleExceptionReturnsInternalServerErrorStatus() {
        when(exception.getMessage()).thenReturn("General exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
