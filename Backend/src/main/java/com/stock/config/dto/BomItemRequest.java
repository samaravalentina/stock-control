package com.stock.config.dto;

import java.math.BigDecimal;

public record BomItemRequest(
        Long rawMaterialId,
        BigDecimal requiredQuantity
) {}

