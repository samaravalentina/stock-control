package com.stock.config.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record BillOfMaterialItemRequest(
    @NotNull(message = "Product ID is required")
    Long productId,

    @NotNull(message = "Raw Material ID is required")
    Long rawMaterialId,

    @NotNull(message = "Required quantity is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Required quantity must be greater than zero")
    BigDecimal requiredQuantity
) {}