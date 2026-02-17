package com.stock.config.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.config.dto.ProductionSuggestionResponse;
import com.stock.config.dto.SuggestedItemResponse;
import com.stock.config.entity.BillOfMaterialItem;
import com.stock.config.entity.Product;
import com.stock.config.entity.RawMaterial;
import com.stock.config.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductionSuggestionService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductionSuggestionResponse suggestProduction() {
        List<Product> products = productRepository.findAllWithBom();

        // Priorizar produtos de maior valor
        products.sort((a, b) -> b.getPrice().compareTo(a.getPrice()));

        // Estoque virtual (rawMaterialId -> quantidade disponível)
        Map<Long, BigDecimal> virtualStock = new HashMap<>();
        for (Product p : products) {
            for (BillOfMaterialItem item : p.getBillOfMaterials()) {
                RawMaterial rm = item.getRawMaterial();
                virtualStock.putIfAbsent(rm.getId(), rm.getStockQuantity());
            }
        }

        List<SuggestedItemResponse> items = new ArrayList<>();
        BigDecimal grandTotal = BigDecimal.ZERO;

        for (Product product : products) {
            Set<BillOfMaterialItem> bom = product.getBillOfMaterials();
            if (bom == null || bom.isEmpty()) continue;

            long maxUnits = computeMaxUnits(bom, virtualStock);
            if (maxUnits <= 0) continue;

            // Consumir estoque virtual
            for (BillOfMaterialItem it : bom) {
                Long rmId = it.getRawMaterial().getId();
                BigDecimal current = virtualStock.getOrDefault(rmId, BigDecimal.ZERO);
                BigDecimal used = it.getRequiredQuantity().multiply(BigDecimal.valueOf(maxUnits));
                virtualStock.put(rmId, current.subtract(used));
            }

            BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(maxUnits));
            grandTotal = grandTotal.add(total);

            items.add(new SuggestedItemResponse(
                    product.getId(),
                    product.getCode(),
                    product.getName(),
                    product.getPrice(),
                    maxUnits,
                    total
            ));
        }

        return new ProductionSuggestionResponse(items, grandTotal);
    }

    private long computeMaxUnits(Set<BillOfMaterialItem> bom, Map<Long, BigDecimal> virtualStock) {
        long max = Long.MAX_VALUE;

        for (BillOfMaterialItem it : bom) {
            Long rmId = it.getRawMaterial().getId();
            BigDecimal stock = virtualStock.getOrDefault(rmId, BigDecimal.ZERO);
            BigDecimal required = it.getRequiredQuantity();

            if (required.compareTo(BigDecimal.ZERO) <= 0) return 0;

            // floor(stock / required)
            BigDecimal div = stock.divide(required, 0, RoundingMode.FLOOR);
            long canMake = div.longValue();

            max = Math.min(max, canMake);
            if (max == 0) return 0;
        }

        return max == Long.MAX_VALUE ? 0 : max;
    }
}