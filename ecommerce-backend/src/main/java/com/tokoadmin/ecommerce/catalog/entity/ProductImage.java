package com.tokoadmin.ecommerce.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private Boolean isPrimary;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore 
    private Product product;
}