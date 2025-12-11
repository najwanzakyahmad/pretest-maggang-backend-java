package com.tokoadmin.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productVariantId;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude 
    private Order order;
}