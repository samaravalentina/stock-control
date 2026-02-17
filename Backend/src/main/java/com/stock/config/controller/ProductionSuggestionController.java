package com.stock.config.controller;

import com.stock.config.dto.ProductionSuggestionResponse;
import com.stock.config.service.ProductionSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/production-suggestions")
@RequiredArgsConstructor
public class ProductionSuggestionController {

    private final ProductionSuggestionService productionSuggestionService;

    @GetMapping
    public ResponseEntity<ProductionSuggestionResponse> suggestProduction() {
        return ResponseEntity.ok(productionSuggestionService.suggestProduction());
    }
}