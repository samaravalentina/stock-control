package com.stock.config.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.stock.config.dto.RawMaterialDTO;
import com.stock.config.dto.RawMaterialRequest;
import com.stock.config.entity.RawMaterial;
import com.stock.config.repository.RawMaterialRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public List<RawMaterialDTO> findAll() {
        return rawMaterialRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public RawMaterialDTO findById(Long id) {
        RawMaterial rm = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found: " + id));
        return toDTO(rm);
    }

    public RawMaterialDTO create(RawMaterialRequest req) {
        validate(req);
        rawMaterialRepository.findByCode(req.code()).ifPresent(rm -> {
            throw new IllegalArgumentException("Raw material code already exists: " + req.code());
        });

        RawMaterial rm = RawMaterial.builder()
                .code(req.code())
                .name(req.name())
                .stockQuantity(req.stockQuantity())
                .build();

        RawMaterial saved = rawMaterialRepository.save(rm);
        return toDTO(saved);
    }

    public RawMaterialDTO update(Long id, RawMaterialRequest req) {
        validate(req);
        RawMaterial rm = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found: " + id));

        rawMaterialRepository.findByCode(req.code()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new IllegalArgumentException("Raw material code already exists: " + req.code());
            }
        });

        rm.setCode(req.code());
        rm.setName(req.name());
        rm.setStockQuantity(req.stockQuantity());
        RawMaterial saved = rawMaterialRepository.save(rm);
        return toDTO(saved);
    }

    public void delete(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    private void validate(RawMaterialRequest req) {
        if (req.code() == null || req.code().isBlank()) throw new IllegalArgumentException("code is required");
        if (req.name() == null || req.name().isBlank()) throw new IllegalArgumentException("name is required");
        if (req.stockQuantity() == null || req.stockQuantity().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("stockQuantity must be >= 0");
    }

    private RawMaterialDTO toDTO(RawMaterial rm) {
        return new RawMaterialDTO(rm.getId(), rm.getCode(), rm.getName(), rm.getStockQuantity());
    }
}