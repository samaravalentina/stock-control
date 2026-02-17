package com.stock.config.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank(message = "Código é obrigatório")
    String code,

    @NotBlank(message = "Nome é obrigatório")
    String name,

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    BigDecimal price
) {}
