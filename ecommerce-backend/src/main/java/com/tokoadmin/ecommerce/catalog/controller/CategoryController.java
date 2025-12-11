package com.tokoadmin.ecommerce.catalog.controller;

import com.tokoadmin.ecommerce.catalog.entity.Category;
import com.tokoadmin.ecommerce.catalog.entity.Store;
import com.tokoadmin.ecommerce.catalog.repository.CategoryRepository;
import com.tokoadmin.ecommerce.catalog.repository.StoreRepository;
import com.tokoadmin.ecommerce.common.ApiResponse;
import com.tokoadmin.ecommerce.common.ResourceNotFoundException; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private StoreRepository storeRepository;

    @PostMapping("/{storeId}")
    public ResponseEntity<ApiResponse<Category>> createCategory(@PathVariable Long storeId, @RequestBody Category category) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store tidak ditemukan dengan ID: " + storeId));
        
        category.setStore(store);
        Category savedCategory = categoryRepository.save(category);
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Kategori berhasil dibuat", savedCategory)
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<ApiResponse<List<Category>>> getByStore(@PathVariable Long storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException("Store tidak ditemukan dengan ID: " + storeId);
        }

        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Data kategori berhasil diambil", categories)
        );
    }
}