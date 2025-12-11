package org.jsp.Assessment.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jsp.Assessment.dto.ProductRequest;
import org.jsp.Assessment.entity.Product;
import org.jsp.Assessment.service.ProductServiceInterface;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductServiceInterface svc;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest req) {
        Product created = svc.addProduct(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(svc.listAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam("q") String q) {
        return ResponseEntity.ok(svc.searchByName(q));
    }

    
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(svc.filterByCategory(category));
    }

    @GetMapping("/sort/price")
    public ResponseEntity<List<Product>> sortByPrice() {
        return ResponseEntity.ok(svc.sortByPriceAsc());
    }

    @GetMapping("/inventory/value")
    public ResponseEntity<Double> inventoryValue() {
        double total = svc.inventoryValue();
        return ResponseEntity.ok(total);
    }
}



