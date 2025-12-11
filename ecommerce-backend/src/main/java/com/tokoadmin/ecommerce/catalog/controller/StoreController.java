package com.tokoadmin.ecommerce.catalog.controller;

import com.tokoadmin.ecommerce.catalog.entity.Store;
import com.tokoadmin.ecommerce.catalog.repository.StoreRepository;
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
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired private StoreRepository storeRepository;
    @Autowired private UserRepository userRepository; 

    @PostMapping
    public ResponseEntity<ApiResponse<Store>> createStore(@RequestBody Store store) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        store.setUser(user);

        Store savedStore = storeRepository.save(store);
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Toko berhasil dibuat", savedStore)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Store>>> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Data toko berhasil diambil", stores));
    }
}