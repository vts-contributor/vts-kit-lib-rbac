package com.example.libdev.web.rest;


import com.example.libdev.dynamicauthorization.DynamicAuthorizationService;
import com.example.libdev.web.rest.dto.permission.PermissionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class PermissionController {

    private final DynamicAuthorizationService dynamicAuthorizationService;

    public PermissionController(DynamicAuthorizationService dynamicAuthorizationService) {
        this.dynamicAuthorizationService = dynamicAuthorizationService;
    }


    @GetMapping("/getPermissions")
    public ResponseEntity<PermissionDTO> getPermissions(){
        return new ResponseEntity<>(dynamicAuthorizationService.getPermissions(), HttpStatus.OK);
    }
}
