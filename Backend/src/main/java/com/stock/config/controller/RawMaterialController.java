package com.stock.config.controller;

import com.stock.config.dto.RawMaterialDTO;
import com.stock.config.dto.RawMaterialRequest;
import com.stock.config.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
@RequiredArgsConstructor
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @GetMapping
    public ResponseEntity<List<RawMaterialDTO>> findAll() {
        return ResponseEntity.ok(rawMaterialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rawMaterialService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RawMaterialDTO> create(@RequestBody RawMaterialRequest req) {
        return ResponseEntity.ok(rawMaterialService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> update(@PathVariable Long id, @RequestBody RawMaterialRequest req) {
        return ResponseEntity.ok(rawMaterialService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}