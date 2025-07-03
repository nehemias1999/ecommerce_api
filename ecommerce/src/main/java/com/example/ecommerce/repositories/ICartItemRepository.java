package com.example.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entites.CartItem;

public interface ICartItemRepository extends JpaRepository<CartItem, UUID> {

}
