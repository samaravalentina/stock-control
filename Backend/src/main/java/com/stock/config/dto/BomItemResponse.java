package com.stock.config.dto;

import java.math.BigDecimal;

public record BomItemResponse(
    Long id,
    Long productId,
    Long rawMaterialId,
    String rawMaterialCode,
    String rawMaterialName,
    BigDecimal rawMaterialStockQuantity,
    BigDecimal requiredQuantity
) {}
