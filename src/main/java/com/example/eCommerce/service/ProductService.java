package com.example.eCommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eCommerce.entity.Product;
import com.example.eCommerce.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Save product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Product Id: " + id));
    }

    // List of all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Delete all products
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Invalid Product Id: " + id);
        }

        productRepository.deleteById(id);
    }
}