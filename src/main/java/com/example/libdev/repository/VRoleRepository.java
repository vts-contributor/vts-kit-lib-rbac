package com.example.libdev.repository;

import com.example.libdev.domain.VRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VRoleRepository extends JpaRepository<VRole, Long> {
    Optional<VRole> findByCode(String code);
}
