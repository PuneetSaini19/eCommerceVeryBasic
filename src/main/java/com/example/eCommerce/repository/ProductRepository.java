package com.example.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eCommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}