package com.example.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.DTOs.IN.CategoryDTOIN;
import com.example.ecommerce.model.DTOs.OUT.CategoryDTOOUT;
import com.example.ecommerce.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }   

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTOOUT>> getAllCategories() { 
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/create")
    public ResponseEntity<CategoryDTOOUT> createCategory(@RequestBody CategoryDTOIN categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category data cannot be null");
        }

        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CategoryDTOOUT> upateCategory(@PathVariable UUID id, @RequestBody CategoryDTOIN categoryDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Category identifier cannot be null");
        }
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category data cannot be null");
        }      

        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO)); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDTOOUT> deleteCategory(@PathVariable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Category identifier cannot be null");
        }

        return ResponseEntity.ok(categoryService.deleteCategory(id)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTOOUT> getCategoryById(@PathVariable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Category identifier cannot be null");
        }

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

}
