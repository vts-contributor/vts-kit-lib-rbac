package com.example.libdev.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
//@Component
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final VTokenProvider VTokenProvider;

    public JWTConfigurer(VTokenProvider VTokenProvider) {
        this.VTokenProvider = VTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        JWTFilter customFilter = new JWTFilter(VTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);




    }
}
