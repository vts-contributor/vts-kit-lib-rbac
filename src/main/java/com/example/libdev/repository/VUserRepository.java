package com.example.libdev.repository;

import com.example.libdev.domain.VUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VUserRepository extends JpaRepository<VUser, Long> {
    String USERS_BY_LOGIN_CACHE = "usersByLogin";
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    @EntityGraph(attributePaths = "vRoles")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<VUser> findOneWithRolesByUsername(String lowercaseLogin);

    Optional<VUser> findOneByUsername(String username);

    Optional<VUser> findOneByEmailIgnoreCase(String email);
}
