package com.stock.config.controller;

import com.stock.config.dto.BillOfMaterialItemDTO;
import com.stock.config.dto.BillOfMaterialItemRequest;
import com.stock.config.service.BillOfMaterialItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill-of-material-items")
@RequiredArgsConstructor
public class BillOfMaterialItemController {

    private final BillOfMaterialItemService billOfMaterialItemService;

    @GetMapping
    public ResponseEntity<List<BillOfMaterialItemDTO>> findAll() {
        return ResponseEntity.ok(billOfMaterialItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillOfMaterialItemDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(billOfMaterialItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BillOfMaterialItemDTO> create(@RequestBody BillOfMaterialItemRequest req) {
        return ResponseEntity.ok(billOfMaterialItemService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillOfMaterialItemDTO> update(@PathVariable Long id, @RequestBody BillOfMaterialItemRequest req) {
        return ResponseEntity.ok(billOfMaterialItemService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        billOfMaterialItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}