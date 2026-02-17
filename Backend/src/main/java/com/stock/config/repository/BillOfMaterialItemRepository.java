package com.stock.config.repository;

import com.stock.config.entity.BillOfMaterialItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillOfMaterialItemRepository extends JpaRepository<BillOfMaterialItem, Long> {

    // Buscar todos os itens de BOM de um produto específico
    List<BillOfMaterialItem> findByProductId(Long productId);

    // Buscar todos os itens de BOM de uma matéria-prima específica
    List<BillOfMaterialItem> findByRawMaterialId(Long rawMaterialId);
}