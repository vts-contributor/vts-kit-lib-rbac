package com.example.libdev.dynamicauthorization;

import com.example.libdev.domain.VEndpoint;
import com.example.libdev.domain.VRole;
import com.example.libdev.service.RBACService;
import com.example.libdev.service.VPermissionService;
import com.example.libdev.web.rest.dto.permission.PermissionDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DynamicAuthorizationService {

    private final VPermissionService vPermissonService;
    private final RBACService rbacService;

    public DynamicAuthorizationService(VPermissionService vPermissonService, RBACService rbacService) {
        this.vPermissonService = vPermissonService;
        this.rbacService = rbacService;
    }

    public PermissionDTO getPermissions() {
        String username = rbacService.getUserLogin();
        return vPermissonService.getPermissions(username);
    }

    public boolean checkAllowed(VEndpoint endpoint) {
        if(endpoint.getPerms() == null){
            return checkAllowedNoMapping(endpoint);
        }
        return checkAllowedWithMapping(endpoint);
    }

    private boolean checkAllowedWithMapping(VEndpoint endpoint) {
        String perms = endpoint.getPerms();
        String username = rbacService.getUserLogin();
        return vPermissonService.getUserHasButtonPerms(username, perms);
    }

    private boolean checkAllowedNoMapping(VEndpoint endpoint) {
        Set<String> allowedRoles = endpoint.getvRoles().stream().map(VRole::getCode).collect(Collectors.toSet());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for(String role: rbacService.getRoleId(authentication)){
            if(allowedRoles.contains(role)) return true;
        }
        return false;
    }


}
