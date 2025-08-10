package com.example.eCommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eCommerce.entity.Cart;
import com.example.eCommerce.entity.CartItem;
import com.example.eCommerce.entity.Order;
import com.example.eCommerce.entity.OrderItem;
import com.example.eCommerce.entity.User;
//import com.example.eCommerce.repository.OrderItemRepository;
import com.example.eCommerce.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
//    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
//        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
    }

    // Place an Order 
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUser(userId);
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user); // Set user ID
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<CartItem> cartItems = cart.getItems();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrize());
            order.getItems().add(orderItem);
        }

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId);
        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Invalid Order Id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Order Id: " + id));
    }
}