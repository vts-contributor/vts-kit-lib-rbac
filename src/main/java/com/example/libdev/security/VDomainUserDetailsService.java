package com.example.libdev.security;

import com.example.libdev.domain.VRole;
import com.example.libdev.domain.VUser;
import com.example.libdev.repository.VUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component("userDetailsService")

public class VDomainUserDetailsService implements UserDetailsService {


    private final Logger log = LoggerFactory.getLogger(VDomainUserDetailsService.class);

    private final VUserRepository userRepository;

    public VDomainUserDetailsService(VUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.debug("Authenticating {}", login);

//        if (new EmailValidator().isValid(login, null)) {
//            return userRepository
//                    .findOneWithAuthoritiesByEmailIgnoreCase(login)
//                    .map(user -> createSpringSecurityUser(login, user))
//                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
//        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository
                .findOneWithRolesByUsername(lowercaseLogin)
                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private UserDetails createSpringSecurityUser(String lowercaseLogin, VUser user) {
        if (!user.getEnabled()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user
                .getVRoles()
                .stream()
                .map(VRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

    }
}
