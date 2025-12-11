package org.jsp.Assessment.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.jsp.Assessment.dto.ProductRequest;
import org.jsp.Assessment.entity.Product;
import org.jsp.Assessment.exception.NumberException;
import org.jsp.Assessment.repository.ProductRepo;
import org.jsp.Assessment.repository.ProductRepository;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface{
    private final ProductRepo repo;

    public Product addProduct(ProductRequest req) {
        if (repo.existsByPartNumber(req.getPartNumber())) {
            throw new NumberException(req.getPartNumber());
        }
        if (req.getPrice() < 0) throw new IllegalArgumentException("price cannot be negative");
        if (req.getStock() < 0) throw new IllegalArgumentException("stock cannot be negative");

        String normalizedPartName = req.getPartName().trim().toLowerCase();

        Product p = new Product();
        p.setPartNumber(req.getPartNumber().trim());
        p.setPartName(normalizedPartName);
        p.setCategory(req.getCategory().trim());
        p.setPrice(req.getPrice());
        p.setStock(req.getStock());
        p.setCreatedAt(Instant.now());

        try {
            return repo.save(p);
        } catch (IllegalStateException ex) {
            throw new DuplicatePartNumberException(req.getPartNumber());
        }
    }

    public List<Product> listAll() {
        return repo.findAll();
    }

    public List<Product> searchByName(String q) {
        if (q == null) q = "";
        String qq = q.toLowerCase();
        return repo.findAll().stream()
                .filter(p -> p.getPartName() != null && p.getPartName().contains(qq))
                .collect(Collectors.toList());
    }

    public List<Product> filterByCategory(String category) {
        if (category == null) return List.of();
        String cat = category.trim();
        return repo.findAll().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(cat))
                .collect(Collectors.toList());
    }

    public List<Product> sortByPriceAsc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public double inventoryValue() {
        return repo.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}


