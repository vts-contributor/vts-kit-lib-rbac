package com.example.libdev.web.rest;

import com.example.libdev.security.jwt.JWTFilter;
import com.example.libdev.security.jwt.VTokenProvider;
import com.example.libdev.service.VUserService;
import com.example.libdev.web.rest.errors.InvalidPasswordException;
import com.example.libdev.web.rest.vm.LoginVM;
import com.example.libdev.web.rest.vm.ManagedUserVM;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
//@RequestMapping("/api")
public class UserJWTController {

    private final VTokenProvider VTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final VUserService vUserService;

    public UserJWTController(VTokenProvider VTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, VUserService vUserService) {
        this.VTokenProvider = VTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.vUserService = vUserService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = VTokenProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        vUserService.registerUser(managedUserVM);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
                password.isEmpty() ||
                        password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
                        password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }
}
