package com.stock.config.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record RawMaterialRequest(
    @NotBlank(message = "Code is required")
    String code,

    @NotBlank(message = "Name is required")
    String name,

    @NotNull(message = "Stock quantity is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Stock must be >= 0")
    BigDecimal stockQuantity
) {}