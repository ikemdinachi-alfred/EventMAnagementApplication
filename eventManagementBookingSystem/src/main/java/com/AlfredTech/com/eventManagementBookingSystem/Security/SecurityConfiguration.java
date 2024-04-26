package com.AlfredTech.com.eventManagementBookingSystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http)
            throws Exception {

        return http.build();

    }
}
