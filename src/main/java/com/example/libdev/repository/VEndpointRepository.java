package com.example.libdev.repository;

import com.example.libdev.domain.VEndpoint;
import liquibase.pro.packaged.S;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VEndpointRepository extends JpaRepository<VEndpoint, Long> {
    Optional<VEndpoint> findByMethodAndUrl(String method, String url);
}
