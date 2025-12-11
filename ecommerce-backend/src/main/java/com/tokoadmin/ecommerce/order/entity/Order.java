package com.tokoadmin.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private Long userId;
    private String shippingAddress;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now(); 

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @ToString.Exclude 
    private List<OrderItem> items;
}