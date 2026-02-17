package com.stock.config.service;

import com.stock.config.dto.ProductDTO;
import com.stock.config.dto.ProductRequest;
import com.stock.config.entity.Product;
import com.stock.config.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public List<ProductDTO> findAll() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductDTO findById(Long id) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: id=" + id));
        return toDTO(p);
    }

    public ProductDTO create(ProductRequest req) {
        if (repo.existsByCode(req.code())) {
            throw new DataIntegrityViolationException("Já existe um produto com o código " + req.code());
        }
        Product p = new Product();
        p.setCode(req.code());
        p.setName(req.name());
        p.setPrice(req.price());
        Product saved = repo.save(p);
        return toDTO(saved);
    }

    public ProductDTO update(Long id, ProductRequest req) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: id=" + id));
        p.setCode(req.code());
        p.setName(req.name());
        p.setPrice(req.price());
        Product saved = repo.save(p);
        return toDTO(saved);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado: id=" + id);
        }
        repo.deleteById(id);
    }

    private ProductDTO toDTO(Product p) {
        return new ProductDTO(p.getId(), p.getCode(), p.getName(), p.getPrice());
    }
}