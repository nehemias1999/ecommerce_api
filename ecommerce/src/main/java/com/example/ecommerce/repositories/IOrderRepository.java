package com.example.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entites.Order;

public interface IOrderRepository extends JpaRepository<Order, UUID> {

}
