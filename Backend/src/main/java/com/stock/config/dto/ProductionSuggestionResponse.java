package com.stock.config.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductionSuggestionResponse(
    List<SuggestedItemResponse> items,
    BigDecimal grandTotal
) {}