package com.stock.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.config.entity.RawMaterial;

import java.util.Optional;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    Optional<RawMaterial> findByCode(String code);
    boolean existsByCode(String code);
}
