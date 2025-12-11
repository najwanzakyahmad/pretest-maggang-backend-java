package com.tokoadmin.ecommerce.catalog.controller;

import com.tokoadmin.ecommerce.catalog.entity.Product;
import com.tokoadmin.ecommerce.catalog.service.ProductService;
import com.tokoadmin.ecommerce.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll(@RequestParam(required = false) Long storeId) {
        List<Product> products = productService.findAll(storeId);
        
        return ResponseEntity.ok(
            new ApiResponse<>(200, "Berhasil mengambil data produk", products)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(
            @RequestParam Long storeId,
            @RequestParam Long categoryId,
            @RequestBody Product product) {
        
        Product createdProduct = productService.createProduct(storeId, categoryId, product);
        return ResponseEntity.ok(new ApiResponse<>(200, "Produk berhasil dibuat", createdProduct));
    }
}