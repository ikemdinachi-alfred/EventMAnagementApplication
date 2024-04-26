package com.AlfredTech.com.eventManagementBookingSystem.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http)
            throws Exception {

        return http.build();

    }
}
