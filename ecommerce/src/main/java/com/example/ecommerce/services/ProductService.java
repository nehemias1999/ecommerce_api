package com.example.ecommerce.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.model.DTOs.OUT.CategoryDTOOUT;
import com.example.ecommerce.model.DTOs.OUT.ProductDTOOUT;
import com.example.ecommerce.model.DTOs.IN.ProductDTOIN;
import com.example.ecommerce.model.entites.Category;
import com.example.ecommerce.model.entites.Product;
import com.example.ecommerce.repositories.ICategoryRepository;
import com.example.ecommerce.repositories.IProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(
        IProductRepository productRepository,
        ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }   

    @Transactional(readOnly = true)
    public List<ProductDTOOUT> getAllProducts() {
        return productRepository
            .findAll()
            .stream()
            .map(this::createProductDTO)
            .toList();
    }

    @Transactional
    public ProductDTOOUT createProduct(ProductDTOIN productDTO) {
        Product product = createNewProduct(productDTO);

        Product insertedProduct = productRepository.save(product);

        return createProductDTO(insertedProduct);
    }

    @Transactional
    public ProductDTOOUT updateProduct(UUID id, ProductDTOIN productDTO) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        product = updateExistingProduct(product, productDTO);

        Product updatedProduct = productRepository.save(product);

        return createProductDTO(updatedProduct);
    }

    @Transactional
    public ProductDTOOUT deleteProduct(UUID id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        productRepository.deleteById(product.getId());

        return createProductDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductDTOOUT getProductById(UUID id) {
        return productRepository
            .findById(id)
            .map(this::createProductDTO)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    private Product createNewProduct(ProductDTOIN productDTO) {
        Set<Category> categories = productDTO.getCategories()
            .stream()
            .map(categoryId -> categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + categoryId)))
            .collect(Collectors.toSet());

        return new Product(
            null,
            productDTO.getName(),
            productDTO.getDescription(),
            productDTO.getPrice(),
            productDTO.getStock(),
            categories
        );
    }

    private Product updateExistingProduct(Product product, ProductDTOIN productDTO) {
        Set<Category> categories = productDTO.getCategories()
            .stream()
            .map(categoryId -> categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + categoryId)))
            .collect(Collectors.toSet());

        return new Product(
            product.getId(),
            (!product.getName().equals(productDTO.getName())) ? productDTO.getName() : product.getName(),
            (!product.getDescription().equals(productDTO.getDescription())) ? productDTO.getDescription() : product.getDescription(),
            (!product.getPrice().equals(productDTO.getPrice())) ? productDTO.getPrice() : product.getPrice(),
            (!product.getStock().equals(productDTO.getStock())) ? productDTO.getStock() : product.getStock(),
            categories
        );
    }

    private CategoryDTOOUT createCategoryDTO(Category category) {
        return new CategoryDTOOUT(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }

    private ProductDTOOUT createProductDTO(Product product) {
        Set<CategoryDTOOUT> categories = product.getCategories()
            .stream()
            .map(this::createCategoryDTO)
            .collect(Collectors.toSet());

        return new ProductDTOOUT(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            categories
        );
    }
    
}
