package com.stock.config.dto;

import java.math.BigDecimal;

public record RawMaterialDTO(
    Long id,
    String code,
    String name,
    BigDecimal stockQuantity
) {}