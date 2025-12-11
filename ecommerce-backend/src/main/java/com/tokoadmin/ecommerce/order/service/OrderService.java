package com.tokoadmin.ecommerce.order.service;

import com.tokoadmin.ecommerce.cart.entity.Cart;
import com.tokoadmin.ecommerce.cart.repository.CartRepository;
import com.tokoadmin.ecommerce.catalog.entity.ProductVariant;
import com.tokoadmin.ecommerce.catalog.repository.ProductVariantRepository;
import com.tokoadmin.ecommerce.common.ResourceNotFoundException;
import com.tokoadmin.ecommerce.order.entity.Order;
import com.tokoadmin.ecommerce.order.entity.OrderItem;
import com.tokoadmin.ecommerce.order.repository.OrderRepository;
import com.tokoadmin.ecommerce.user.entity.User;
import com.tokoadmin.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductVariantRepository productVariantRepository;
    @Autowired private CartRepository cartRepository;

    @Transactional
    public Order processCheckout(String email, Order orderRequest) {
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        orderRequest.setUserId(user.getId());
        orderRequest.setInvoiceNumber("INV/" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        orderRequest.setStatus("PAID");

        // --- LOGIKA HITUNG TOTAL ---
        BigDecimal calculatedTotal = BigDecimal.ZERO; 

        for (OrderItem item : orderRequest.getItems()) {
            ProductVariant variant = productVariantRepository.findById(item.getProductVariantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Varian tidak ditemukan"));

            if (variant.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stok habis untuk: " + variant.getName());
            }

            variant.setStock(variant.getStock() - item.getQuantity());
            productVariantRepository.save(variant);

            item.setPrice(variant.getPrice()); 
            
            BigDecimal subtotal = variant.getPrice().multiply(new BigDecimal(item.getQuantity()));
            calculatedTotal = calculatedTotal.add(subtotal);

            item.setOrder(orderRequest);
        }

        orderRequest.setTotalAmount(calculatedTotal); 

        Order savedOrder = orderRepository.save(orderRequest);

        List<Cart> userCarts = cartRepository.findByUserId(user.getId());
        if (!userCarts.isEmpty()) {
            cartRepository.deleteAll(userCarts);
        }

        return savedOrder;
    }
}