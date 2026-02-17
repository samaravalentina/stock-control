package com.stock.config.dto;

import java.math.BigDecimal;

public record SuggestedItemResponse(
    Long productId,
    String code,
    String name,
    BigDecimal price,
    long quantity,
    BigDecimal total
) {}