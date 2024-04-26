package com.AlfredTech.com.eventManagementBookingSystem.Security.filter;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.AuthLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
@RequiredArgsConstructor
public class AppAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
          /*
        steps filter use to Authenticate request
        1. extract Authentication credentials from body of request
         */
        InputStream inputStream = request.getInputStream(); // reads request body
       AuthLoginRequest authLoginRequest = mapper.readValue( inputStream, AuthLoginRequest.class );
       // 2 i. create an object of type Authentication that is not yet authenticated
        /*
        Note Authentication is an interface and is implemented by about
         12 classes thus we use usernamePasswordAuthenticationToken
         that implement it.
         */
        Authentication authentication = new UsernamePasswordAuthenticationToken
                (authLoginRequest.getEmail(),authLoginRequest.getPassword());
       Authentication authenticationResult = authenticationManager.authenticate(authentication);

       boolean isAuthenticationSuccessful = authenticationResult.isAuthenticated();
       if (isAuthenticationSuccessful) {
           SecurityContextHolder.getContext().setAuthentication(authenticationResult);
       }

    }
}
