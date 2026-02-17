package com.stock.config.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Table(name = "bill_of_material_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOfMaterialItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // PRODUTO (lado inverso da relação)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // MATÉRIA-PRIMA
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;

    // QUANTIDADE NECESSÁRIA PARA PRODUZIR 1 UNIDADE DO PRODUTO
    @Column(name = "required_quantity", nullable = false, precision = 19, scale = 2)
    private BigDecimal requiredQuantity;
}