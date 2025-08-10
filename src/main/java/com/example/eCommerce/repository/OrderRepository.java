package com.example.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eCommerce.entity.Order;
import com.example.eCommerce.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser(User user);
}