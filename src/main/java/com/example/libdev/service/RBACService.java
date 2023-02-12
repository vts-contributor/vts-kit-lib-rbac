package com.example.libdev.service;

import com.example.libdev.domain.VRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RBACService {

    public String getUserLogin(Authentication authentication){
        return authentication.getName().toUpperCase();
    }

    public String getUserLoginId(Authentication authentication){
        return authentication.getName().toUpperCase();
    }

    public Set<String> getRoleId(Authentication authentication){
        return authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }


}
