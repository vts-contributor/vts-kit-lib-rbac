package com.example.libdev.web.rest.dto.permission;

import java.io.Serializable;

public class AuthDTO implements Serializable {
    public String action;
    public String describe;

    public AuthDTO(String action, String describe) {
        this.action = action;
        this.describe = describe;
    }


}
