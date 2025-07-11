package com.example.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.entites.Category;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {

}
