package com.tokoadmin.ecommerce.catalog.service;

import com.tokoadmin.ecommerce.catalog.entity.*;
import com.tokoadmin.ecommerce.catalog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import java.util.List;

@Service 
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private StoreRepository storeRepository;
    @Autowired private CategoryRepository categoryRepository;

    public List<Product> findAll(Long storeId) {
        if (storeId != null) {
            return productRepository.findByStoreId(storeId);
        } else {
            return productRepository.findAll();
        }
    }

    @Transactional 
    public Product createProduct(Long storeId, Long categoryId, Product product) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new RuntimeException("Toko tidak ditemukan!"));
            
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan!"));

        product.setStore(store);
        product.setCategory(category);

        if(product.getVariants() != null) {
            product.getVariants().forEach(v -> v.setProduct(product));
        }
        if(product.getImages() != null) {
            product.getImages().forEach(img -> img.setProduct(product));
        }

        return productRepository.save(product);
    }
}