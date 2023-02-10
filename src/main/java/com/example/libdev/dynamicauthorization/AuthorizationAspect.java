package com.example.libdev.dynamicauthorization;


import com.example.libdev.domain.VEndpoint;
import com.example.libdev.domain.VRole;
import com.example.libdev.repository.VEndpointRepository;
import com.example.libdev.service.RBACService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
public class AuthorizationAspect {

    private final Environment env;

    @Autowired
    private DynamicAuthorizationUtils dynamicAuthorizationUtils;


    @Autowired
    private RBACService rbacService;
    public AuthorizationAspect(Environment env) {
        this.env = env;
    }

    @Pointcut(
            "within(@org.springframework.web.bind.annotation.RestController *)"
    )
    public void controllerPointcut(){}

    @Before("controllerPointcut()")
    public void authorize(JoinPoint joinPoint){
        Optional<VEndpoint> optionalEndpoint = dynamicAuthorizationUtils.getEndpoint();
//        String test = dynamicAuthorizationUtils.getRequestBody();
        if(!optionalEndpoint.isPresent()) return;
        VEndpoint endpoint = optionalEndpoint.get();

        if(!checkAllowed(endpoint)) throw new AccessDeniedException("");
        if(!endpoint.getMethod().toUpperCase().equals("GET")){

        }
    }

    private boolean checkAllowed(VEndpoint endpoint){
        Set<String> allowedRoles = endpoint.getvRoles().stream().map(VRole::getCode).collect(Collectors.toSet());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for(String role: rbacService.getRoleId(authentication)){
            if(allowedRoles.contains(role)) return true;
        }
        return false;
    }
}
