package com.AlfredTech.com.eventManagementBookingSystem.Security.filter;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.AuthLoginRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
        // opaque
           String accessToken = JWT.create()
                   .withIssuer("Alfred_Tech Event Management System")
                   .withSubject("Access Token")
                   .withClaim("Email:"+authLoginRequest.getEmail(),new Date())
                   .withExpiresAt(Instant.now().plusSeconds(86400*7))
                   .sign(Algorithm.HMAC256("This is our very secret key"));

           Map<String,String>authresponse = new HashMap<>();
           authresponse.put("access_token", accessToken);
           response.setStatus((OK.value()));
           response.setContentType(APPLICATION_JSON_VALUE);
           response.getOutputStream().write(mapper.writeValueAsBytes(authresponse));

       }
       filterChain.doFilter(request, response);


    }
}
