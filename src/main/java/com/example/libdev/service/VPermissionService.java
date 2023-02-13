package com.example.libdev.service;

import com.example.libdev.domain.VPermission;
import com.example.libdev.domain.VPermissionType;
import com.example.libdev.repository.VPermissionRepository;
import com.example.libdev.web.rest.dto.AuthDTO;
import com.example.libdev.web.rest.dto.MenuDTO;
import com.example.libdev.web.rest.dto.PermissionDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class VPermissionService {
    private VPermissionRepository vPermissionRepository;
    private final RBACService rbacService;

    public VPermissionService(VPermissionRepository vPermissionRepository, RBACService rbacService) {
        this.vPermissionRepository = vPermissionRepository;
        this.rbacService = rbacService;
    }


    public boolean getUserHasButtonPerms(String username, String perms){

//        String username = rbacService.getUserLogin();
        return vPermissionRepository.getPermissionsByUserAndPerms(username, perms).isPresent();
    }
    public PermissionDTO getPermissions(String username) {

        Set<VPermission> vPermissions = vPermissionRepository.getPermissionsByUser(username);


        Set<AuthDTO> auth = new HashSet<>();
        Set<MenuDTO> flatMenu = new HashSet<>();

        vPermissions.forEach(
                vPermission -> {
                    switch (vPermission.getType()){
                        case VPermissionType.MENU:
                        case VPermissionType.SUBMENU:
                            flatMenu.add(new MenuDTO(vPermission.getId(), vPermission.getParentId(), vPermission.getCode(), vPermission.getName(), vPermission.getUrl(), vPermission.getComponent(), vPermission.getOrder(), vPermission.getType()));
                            break;
                        case VPermissionType.BUTTON:
                            auth.add(new AuthDTO(vPermission.getPerms(), vPermission.getDescription()));
                            break;
                    }
                }
        );

        return new PermissionDTO(auth, getHierarchicalMenu(flatMenu));
    }

    private Set<MenuDTO> getHierarchicalMenu(Set<MenuDTO> flatMenu) {
        Set<MenuDTO> hierarchicalMenu =  new HashSet<>();

        HashMap<Long, MenuDTO> menuPathMap = new HashMap<>();
        flatMenu.forEach(menuDTO -> {

            menuPathMap.put(menuDTO.getId(), menuDTO);
        });

        for(MenuDTO menuDTO: flatMenu){
            if(menuDTO.getParentId() == null) hierarchicalMenu.add(menuDTO);
            else menuPathMap.get(menuDTO.getParentId()).addChild(menuDTO);
        }

        return hierarchicalMenu;
    }
}
