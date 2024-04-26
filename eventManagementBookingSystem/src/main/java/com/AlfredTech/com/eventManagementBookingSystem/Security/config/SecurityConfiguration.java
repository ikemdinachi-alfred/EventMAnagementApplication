package com.AlfredTech.com.eventManagementBookingSystem.Security.config;
import com.AlfredTech.com.eventManagementBookingSystem.Security.filter.AppAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http)
            throws Exception {
        Filter filter = new AppAuthenticationFilter(authenticationManager);

        return http.cors(Customizer.withDefaults())
                .csrf(c->c.disable())
                .addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers("/api/user/login").permitAll())
                .authorizeHttpRequests(c->c.requestMatchers("api/user/register").permitAll())
                .build();

    }
}
