package com.example.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.DTOs.IN.ProductDTOIN;
import com.example.ecommerce.model.DTOs.OUT.ProductDTOOUT;
import com.example.ecommerce.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }   

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTOOUT>> getAllProducts() { 
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/create")
    public ResponseEntity<ProductDTOOUT> createProducts(@RequestBody ProductDTOIN productDTO) {
        if (productDTO == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }

        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductDTOOUT> upateProducts(@PathVariable UUID id, @RequestBody ProductDTOIN productDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Product identifier cannot be null");
        }
        if (productDTO == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }      

        return ResponseEntity.ok(productService.updateProduct(id, productDTO)); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTOOUT> deleteProducts(@PathVariable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Product identifier cannot be null");
        }

        return ResponseEntity.ok(productService.deleteProduct(id)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOOUT> getProductById(@PathVariable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Product identifier cannot be null");
        }

        return ResponseEntity.ok(productService.getProductById(id));
    }

}
