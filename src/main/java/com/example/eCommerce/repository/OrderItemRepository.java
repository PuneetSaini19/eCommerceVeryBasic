package com.example.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eCommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {	
}