package com.example.libdev.dynamicauthorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;

@Configuration
@EnableAspectJAutoProxy
public class AuthorizationAspectConfiguration {

    @Bean
    public AuthorizationAspect authorizationAspect(Environment env) {
        return new AuthorizationAspect(env);
    }
}
