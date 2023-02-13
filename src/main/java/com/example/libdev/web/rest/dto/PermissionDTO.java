package com.example.libdev.web.rest.dto;

import java.io.Serializable;
import java.util.Set;

public class PermissionDTO implements Serializable {
    Set<AuthDTO> auth;
    Set<MenuDTO> menu;

    public PermissionDTO(Set<AuthDTO> auth, Set<MenuDTO> menu) {
        this.auth = auth;
        this.menu = menu;
    }

    public Set<AuthDTO> getAuth() {
        return auth;
    }

    public void setAuth(Set<AuthDTO> auth) {
        this.auth = auth;
    }

    public Set<MenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuDTO> menu) {
        this.menu = menu;
    }
}
