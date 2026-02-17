package com.stock.config.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "raw_materials",
    uniqueConstraints = @UniqueConstraint(name = "uk_raw_materials_code", columnNames = "code")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal stockQuantity;
}