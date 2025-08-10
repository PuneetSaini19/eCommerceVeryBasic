package com.example.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eCommerce.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}