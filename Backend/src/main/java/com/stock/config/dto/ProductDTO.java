package com.stock.config.dto;

import java.math.BigDecimal;

public record ProductDTO(
    Long id,
    String code,
    String name,
    BigDecimal price
) {}