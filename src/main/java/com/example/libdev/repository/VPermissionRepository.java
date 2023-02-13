package com.example.libdev.repository;

import com.example.libdev.domain.VPermission;
import com.example.libdev.domain.VUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface VPermissionRepository extends JpaRepository<VPermission, Long> {

    @Query("select permission from VPermission permission JOIN permission.vRoles role join role.vUsers user where upper(user.username) = :username")
    Set<VPermission> getPermissionsByUser(String username);

    @Query("select permission from VPermission permission JOIN permission.vRoles role join role.vUsers user where upper(user.username) = :username and permission.perms = :perms")
    Optional<VPermission> getPermissionsByUserAndPerms(String username, String perms);
}
