package com.stock.config.repository;

import org.springframework.data.jpa.repository.*;
import com.stock.config.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

    boolean existsByCode(String code);

    @Query("""
        select distinct p
        from Product p
        left join fetch p.billOfMaterials bom
        left join fetch bom.rawMaterial rm
        """)
    List<Product> findAllWithBom();
}