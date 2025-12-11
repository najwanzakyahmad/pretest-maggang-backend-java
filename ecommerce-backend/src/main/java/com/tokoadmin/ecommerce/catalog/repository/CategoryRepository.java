package com.tokoadmin.ecommerce.catalog.repository;

import com.tokoadmin.ecommerce.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStoreId(Long storeId);
}