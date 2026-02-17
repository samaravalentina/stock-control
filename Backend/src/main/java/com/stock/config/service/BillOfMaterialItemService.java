package com.stock.config.service;

import com.stock.config.dto.BillOfMaterialItemDTO;
import com.stock.config.dto.BillOfMaterialItemRequest;
import com.stock.config.entity.BillOfMaterialItem;
import com.stock.config.entity.Product;
import com.stock.config.entity.RawMaterial;
import com.stock.config.repository.BillOfMaterialItemRepository;
import com.stock.config.repository.ProductRepository;
import com.stock.config.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialItemService {

    private final BillOfMaterialItemRepository billOfMaterialItemRepository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public List<BillOfMaterialItemDTO> findAll() {
        return billOfMaterialItemRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public BillOfMaterialItemDTO findById(Long id) {
        BillOfMaterialItem item = billOfMaterialItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill of Material Item not found: " + id));
        return toDTO(item);
    }

    public BillOfMaterialItemDTO create(BillOfMaterialItemRequest req) {
        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + req.productId()));

        RawMaterial rawMaterial = rawMaterialRepository.findById(req.rawMaterialId())
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found: " + req.rawMaterialId()));

        BillOfMaterialItem item = BillOfMaterialItem.builder()
                .product(product)
                .rawMaterial(rawMaterial)
                .requiredQuantity(req.requiredQuantity())
                .build();

        return toDTO(billOfMaterialItemRepository.save(item));
    }

    public BillOfMaterialItemDTO update(Long id, BillOfMaterialItemRequest req) {
        BillOfMaterialItem item = billOfMaterialItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill of Material Item not found: " + id));

        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + req.productId()));

        RawMaterial rawMaterial = rawMaterialRepository.findById(req.rawMaterialId())
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found: " + req.rawMaterialId()));

        item.setProduct(product);
        item.setRawMaterial(rawMaterial);
        item.setRequiredQuantity(req.requiredQuantity());

        return toDTO(billOfMaterialItemRepository.save(item));
    }

    public void delete(Long id) {
        billOfMaterialItemRepository.deleteById(id);
    }

    private BillOfMaterialItemDTO toDTO(BillOfMaterialItem item) {
        return new BillOfMaterialItemDTO(
                item.getId(),
                item.getProduct().getId(),
                item.getRawMaterial().getId(),
                item.getRequiredQuantity()
        );
    }
}