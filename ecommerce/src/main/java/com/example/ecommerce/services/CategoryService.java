package com.example.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.model.DTOs.IN.CategoryDTOIN;
import com.example.ecommerce.model.DTOs.OUT.CategoryDTOOUT;
import com.example.ecommerce.model.entites.Category;
import com.example.ecommerce.repositories.ICategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTOOUT> getAllCategories() {
        return categoryRepository
            .findAll()
            .stream()
            .map(this::createCategoryDTO)
            .toList();
    }

    @Transactional
    public CategoryDTOOUT createCategory(CategoryDTOIN categoryDTO) {
        Category category = createNewCategory(categoryDTO);

        Category insertedCategory = categoryRepository.save(category);

        return createCategoryDTO(insertedCategory);
    }

    @Transactional
    public CategoryDTOOUT updateCategory(UUID id, CategoryDTOIN categoryDTO) {
        Category category = categoryRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        category = updateExistingCategory(category, categoryDTO);

        Category updatedCategory = categoryRepository.save(category);

        return createCategoryDTO(updatedCategory);
    }

    @Transactional
    public CategoryDTOOUT deleteCategory(UUID id) {
        Category category = categoryRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        categoryRepository.deleteById(category.getId());

        return createCategoryDTO(category);
    }

    @Transactional(readOnly = true)
    public CategoryDTOOUT getCategoryById(UUID id) {
        return categoryRepository
            .findById(id)
            .map(this::createCategoryDTO)
            .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
    }

    private Category createNewCategory(CategoryDTOIN categoryDTO) {
        return new Category(
            null,
            categoryDTO.getName(),
            categoryDTO.getDescription()
        );
    }

    private Category updateExistingCategory(Category category, CategoryDTOIN categoryDTO) {
        return new Category(
            category.getId(),
            (!category.getName().equals(categoryDTO.getName())) ? categoryDTO.getName() : category.getName(),
            (!category.getDescription().equals(categoryDTO.getDescription())) ? categoryDTO.getDescription() : category.getDescription()
        );
    }

    private CategoryDTOOUT createCategoryDTO(Category category) {
        return new CategoryDTOOUT(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
     
}
