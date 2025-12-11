package com.tokoadmin.ecommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokoadmin.ecommerce.cart.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
}
