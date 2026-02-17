package com.stock.config.dto;

import java.math.BigDecimal;

public record BillOfMaterialItemDTO(
    Long id,
    Long productId,
    Long rawMaterialId,
    BigDecimal requiredQuantity
) {}
