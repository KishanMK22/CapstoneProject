package com.stackroute.MovieApp.filter;

import com.stackroute.MovieApp.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AppFilterTest {

    private AppFilter appFilter;

    @Mock
    private TokenService tokenService;

    @Mock
    private Jws<Claims> jws;

    @Mock
    private Claims claims;

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdkIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcxMDU3MzI3NSwiZXhwIjoxNzEwNTc2ODc1fQ.48vEs4U6kpfgPuHZKkK6O4MkGKCW_Pxurfk9EcDYiIc";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appFilter = new AppFilter(tokenService); // create a real instance of AppFilter
    }


    @Test
    void shouldFilterWhenTokenIsValid() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/v1/user/some-endpoint");
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();
        when(tokenService.parseClaimsJws(token, "secretkey")).thenReturn(jws);
        when(jws.getBody()).thenReturn(claims);

        appFilter.doFilterInternal(request, response, filterChain);

        assertEquals(200, response.getStatus());
    }

//    create test case for shouldFilterWhenTokenIsInValid
    @Test
    void shouldFilterWhenTokenIsInValid() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/v1/user/some-endpoint");
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();
        when(tokenService.parseClaimsJws(token, "secretkey")).thenThrow(ServletException.class);

        appFilter.doFilterInternal(request, response, filterChain);

        assertEquals(401, response.getStatus());
    }
}