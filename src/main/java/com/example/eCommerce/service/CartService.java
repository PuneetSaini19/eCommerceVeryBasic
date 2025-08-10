package com.example.eCommerce.service;

import org.springframework.stereotype.Service;

import com.example.eCommerce.entity.Cart;
import com.example.eCommerce.entity.CartItem;
import com.example.eCommerce.entity.Product;
import com.example.eCommerce.entity.User;
import com.example.eCommerce.repository.CartItemRepository;
import com.example.eCommerce.repository.CartRepository;

@Service
public class CartService {

	private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, CartItemRepository itemRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = itemRepository;
        this.productService = productService;
    }

    // Get cart by user
    public Cart getCartByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    // Add to Cart
    public Cart addToCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUser(userId);

        Product product = productService.getProductById(productId);

        // Understand again.............................................................
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        return cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
    	Cart cart = getCartByUser(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}