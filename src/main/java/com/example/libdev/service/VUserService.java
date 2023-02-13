package com.example.libdev.service;

import com.example.libdev.domain.VRole;
import com.example.libdev.domain.VUser;
import com.example.libdev.repository.VRoleRepository;
import com.example.libdev.repository.VUserRepository;
import com.example.libdev.security.VAuthoritiesConstants;
import com.example.libdev.web.rest.errors.EmailAlreadyUsedException;
import com.example.libdev.web.rest.errors.UsernameAlreadyUsedException;
import com.example.libdev.web.rest.vm.ManagedUserVM;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class VUserService {


    private final VUserRepository vUserRepository;

    private final CacheManager cacheManager;

    private final PasswordEncoder passwordEncoder;
    private final VRoleRepository vRoleRepository;

    public VUserService(VUserRepository vUserRepository, CacheManager cacheManager, PasswordEncoder passwordEncoder, VRoleRepository vRoleRepository) {
        this.vUserRepository = vUserRepository;
        this.cacheManager = cacheManager;
        this.passwordEncoder = passwordEncoder;
        this.vRoleRepository = vRoleRepository;
    }

    public VUser registerUser(ManagedUserVM userDTO) {

        vUserRepository
                .findOneByUsername(userDTO.getUsername().toLowerCase())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new UsernameAlreadyUsedException();
                    }
                });
        vUserRepository
                .findOneByEmailIgnoreCase(userDTO.getEmail())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new EmailAlreadyUsedException();
                    }
                });
        VUser newUser = new VUser();

        String password = userDTO.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(userDTO.getUsername().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstname(userDTO.getFirstname());
        newUser.setLastname(userDTO.getLastname());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setLanguage(userDTO.getLanguage());
        // todo: implment email verification logic
        newUser.setEnabled(true);

        Set<VRole> vRoles = new HashSet<>();
        vRoleRepository.findByCode(VAuthoritiesConstants.USER).ifPresent(vRoles::add);
        newUser.setVRoles(vRoles);
        vUserRepository.save(newUser);
        this.clearUserCaches(newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(VUser existingUser) {
        if (existingUser.getEnabled()) {
            return false;
        }
        vUserRepository.delete(existingUser);
        vUserRepository.flush();
        this.clearUserCaches(existingUser);
        return true;
    }

    private void clearUserCaches(VUser user) {
        Objects.requireNonNull(cacheManager.getCache(VUserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getUsername());
        if (user.getEmail() != null) {
            Objects.requireNonNull(cacheManager.getCache(VUserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        }
    }
}
