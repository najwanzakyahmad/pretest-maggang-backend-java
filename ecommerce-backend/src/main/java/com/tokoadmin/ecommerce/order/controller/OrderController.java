package com.tokoadmin.ecommerce.order.controller;

import com.tokoadmin.ecommerce.common.ApiResponse;
import com.tokoadmin.ecommerce.common.ResourceNotFoundException;
import com.tokoadmin.ecommerce.order.entity.Order;
import com.tokoadmin.ecommerce.order.repository.OrderRepository; 
import com.tokoadmin.ecommerce.order.service.OrderService;
import com.tokoadmin.ecommerce.user.entity.User;
import com.tokoadmin.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository; 
    @Autowired private UserRepository userRepository;   

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> checkout(@RequestBody Order order) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Order savedOrder = orderService.processCheckout(email, order);
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Checkout berhasil", savedOrder)
        );
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<Order>>> getMyOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        List<Order> orders = orderRepository.findByUserId(user.getId());

        return ResponseEntity.ok(
            new ApiResponse<>(200, "Berhasil mengambil data order", orders)
        );
    }
}