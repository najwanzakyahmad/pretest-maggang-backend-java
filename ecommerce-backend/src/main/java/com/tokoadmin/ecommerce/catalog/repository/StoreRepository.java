package com.tokoadmin.ecommerce.catalog.repository;

import com.tokoadmin.ecommerce.catalog.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}