package com.tokoadmin.ecommerce.catalog.repository;

import com.tokoadmin.ecommerce.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
}