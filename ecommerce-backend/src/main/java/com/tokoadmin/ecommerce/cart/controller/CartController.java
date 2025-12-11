package com.tokoadmin.ecommerce.cart.controller;

import com.tokoadmin.ecommerce.cart.entity.Cart;
import com.tokoadmin.ecommerce.cart.repository.CartRepository;
import com.tokoadmin.ecommerce.common.ApiResponse;
import com.tokoadmin.ecommerce.common.ResourceNotFoundException; 
import com.tokoadmin.ecommerce.user.entity.User;
import com.tokoadmin.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my-cart") 
    public ResponseEntity<ApiResponse<List<Cart>>> getMyCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        List<Cart> carts = cartRepository.findByUserId(user.getId());
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Berhasil mengambil data keranjang", carts)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cart>> addToCart(@RequestBody Cart cart) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        cart.setUserId(user.getId()); 

        if (cart.getCartItems() != null) {
            cart.getCartItems().forEach(item -> item.setCart(cart));
        }

        Cart savedCart = cartRepository.save(cart);
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Item berhasil ditambahkan ke keranjang", savedCart)
        );
    }
}