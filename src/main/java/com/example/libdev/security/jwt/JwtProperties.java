package com.example.libdev.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "vtrbac.jwt",
        ignoreUnknownFields = false
)
public class JwtProperties {

    private String test;

    private String secret;
    private String base64Secret;
    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    public void setTest(String test) {
        this.test = test;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
        this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
    }

    public String getTest() {
        return test;
    }

    public String getSecret() {
        return secret;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public long getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
    }
}
