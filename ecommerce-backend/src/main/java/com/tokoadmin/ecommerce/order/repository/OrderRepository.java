package com.tokoadmin.ecommerce.order.repository;

import com.tokoadmin.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}