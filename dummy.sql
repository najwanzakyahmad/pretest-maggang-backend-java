-- =============================================
-- 1. BERSIHKAN DATA LAMA & RESET ID
-- =============================================
TRUNCATE TABLE cart_items, carts, order_items, orders, product_images, product_variants, products, categories, stores, users RESTART IDENTITY CASCADE;

-- =============================================
-- 2. INSERT 10 USERS
-- Password default: "password"
-- =============================================
INSERT INTO users (name, email, password, role) VALUES
('Andi Seller 1', 'user1@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'SELLER'), -- ID 1
('Budi Seller 2', 'user2@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'SELLER'), -- ID 2
('Citra Seller 3', 'user3@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'SELLER'), -- ID 3
('Dedi Customer', 'user4@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Eka Customer', 'user5@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Fajar Customer', 'user6@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Gita Customer', 'user7@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Hadi Customer', 'user8@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Indah Customer', 'user9@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),
('Joko Customer', 'user10@mail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcd7qa8qkrBm', 'CUSTOMER'),

-- =============================================
-- 3. INSERT 3 TOKO (STORES)
-- =============================================
INSERT INTO stores (name, city, address, description, user_id) VALUES
('iStore Bandung', 'Bandung', 'Jl. Dago No 10', 'Pusat Apple Terlengkap & Original', 1), -- Milik Andi
('Fashion Nova', 'Jakarta', 'Grand Indonesia Lt 3', 'Baju kekinian import & lokal', 2), -- Milik Budi
('Toko Hobi Kita', 'Surabaya', 'Jl. Tunjungan 5', 'Gundam, Tamiya, dan Diecast', 3); -- Milik Citra

-- =============================================
-- 4. INSERT KATEGORI
-- =============================================
-- Toko 1 (iStore)
INSERT INTO categories (name, store_id) VALUES 
('MacBook', 1), ('iPhone', 1), ('iPad', 1), ('Aksesoris', 1);

-- Toko 2 (Fashion)
INSERT INTO categories (name, store_id) VALUES 
('Pria', 2), ('Wanita', 2);

-- Toko 3 (Hobi)
INSERT INTO categories (name, store_id) VALUES 
('Gundam', 3), ('Tamiya', 3);

-- =============================================
-- 5. INSERT PRODUK, VARIAN, & GAMBAR
-- =============================================

-- --- TOKO 1: iStore (4 Produk) ---

-- 1. MacBook Pro M3
INSERT INTO products (name, description, store_id, category_id) VALUES 
('MacBook Pro M3', 'Laptop Chip M3 Tercepat untuk Coding', 1, 1); -- ID 1
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('14 Inch - 8GB/512GB', 28000000, 10, 'MBP-M3-14-GRY', 1), 
('16 Inch - 16GB/1TB', 35000000, 5, 'MBP-M3-16-SLV', 1); 
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/macbook_front.jpg', true, 1);

-- 2. iPhone 15 Pro
INSERT INTO products (name, description, store_id, category_id) VALUES 
('iPhone 15 Pro', 'Titanium Design, USB-C, A17 Pro Chip', 1, 2); -- ID 2
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('128GB - Blue Titanium', 19000000, 20, 'IP15-PRO-BLU', 2),
('256GB - Natural Titanium', 21000000, 15, 'IP15-PRO-NAT', 2);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/iphone15.jpg', true, 2);

-- 3. iPad Air 5 (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('iPad Air 5 M1', 'Tablet Rasa Laptop dengan Chip M1', 1, 3); -- ID 3
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('64GB - WiFi - Purple', 9500000, 25, 'IPD-AIR5-PUR', 3),
('256GB - WiFi - Space Grey', 12500000, 10, 'IPD-AIR5-GRY', 3);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/ipad_air.jpg', true, 3);

-- 4. AirPods Pro 2 (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('AirPods Pro Gen 2', 'Noise Cancellation Terbaik, USB-C Case', 1, 4); -- ID 4
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Type-C MagSafe Case', 3800000, 50, 'PODS-PRO-2', 4);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/airpods.jpg', true, 4);


-- --- TOKO 2: Fashion Nova (3 Produk) ---

-- 5. Kemeja Flannel
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Kemeja Flannel Kotak', 'Bahan adem nyaman dipakai kerja atau main', 2, 5); -- ID 5
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Size M - Merah', 150000, 50, 'FL-RED-M', 5),
('Size L - Biru', 150000, 40, 'FL-BLU-L', 5);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/flannel.jpg', true, 5);

-- 6. Celana Chino (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Celana Chino Slimfit', 'Bahan stretch, tidak kaku', 2, 5); -- ID 6
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Size 30 - Cream', 200000, 30, 'CHINO-CRM-30', 6),
('Size 32 - Hitam', 200000, 30, 'CHINO-BLK-32', 6);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/chino.jpg', true, 6);

-- 7. Gamis Syari (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Gamis Premium Silk', 'Cocok untuk lebaran dan kondangan', 2, 6); -- ID 7
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('All Size - Sage Green', 350000, 15, 'GAMIS-SAGE', 7),
('All Size - Dusty Pink', 350000, 15, 'GAMIS-PINK', 7);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/gamis.jpg', true, 7);


-- --- TOKO 3: Hobi Kita (3 Produk) ---

-- 8. Gundam Aerial
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Gundam Aerial HG', 'Skala 1/144 High Grade dari seri Witch from Mercury', 3, 7); -- ID 8
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Standard Box', 250000, 100, 'GN-AERIAL-HG', 8);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/aerial.jpg', true, 8);

-- 9. Tamiya Magnum Saber (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Magnum Saber Premium', 'Chassis Super-II, Dinamo standar included', 3, 8); -- ID 9
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Original Box', 180000, 20, 'TM-MAGNUM', 9);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/magnum.jpg', true, 9);

-- 10. Tamiya Neo Tridagger (Produk Baru)
INSERT INTO products (name, description, store_id, category_id) VALUES 
('Neo Tridagger ZMC Carbon', 'Special edition body carbon, sangat langka', 3, 8); -- ID 10
INSERT INTO product_variants (name, price, stock, sku, product_id) VALUES
('Special Edition', 450000, 5, 'TM-TRIDAGGER-ZMC', 10);
INSERT INTO product_images (image_url, is_primary, product_id) VALUES
('https://dummyimage.com/tridagger.jpg', true, 10);


-- =============================================
-- 6. INSERT KERANJANG (CART)
-- =============================================

-- Cart User 4 (Beli MacBook & Kemeja)
INSERT INTO carts (user_id) VALUES (4); 
INSERT INTO cart_items (cart_id, product_variant_id, quantity, price) VALUES
(1, 1, 1, 28000000), -- 1x MacBook 14 Inch
(1, 8, 2, 150000);   -- 2x Kemeja Flannel Merah

-- Cart User 5 (Beli iPhone & AirPods)
INSERT INTO carts (user_id) VALUES (5); 
INSERT INTO cart_items (cart_id, product_variant_id, quantity, price) VALUES
(2, 3, 1, 19000000), -- 1x iPhone Blue
(2, 7, 1, 3800000);  -- 1x AirPods Pro

-- Cart User 6 (Beli Mainan)
INSERT INTO carts (user_id) VALUES (6); 
INSERT INTO cart_items (cart_id, product_variant_id, quantity, price) VALUES
(3, 15, 1, 180000),  -- 1x Magnum Saber
(3, 16, 1, 450000);  -- 1x Neo Tridagger

-- =============================================
-- 7. INSERT TRANSAKSI (ORDERS)
-- Skenario: User 6 juga sudah pernah checkout sebelumnya
-- =============================================

INSERT INTO orders (invoice_number, user_id, shipping_address, total_amount, status) VALUES
('INV/2025/TEST/001', 6, 'Jl. Merdeka No 1, Jakarta', 250000, 'PAID');

INSERT INTO order_items (order_id, product_variant_id, quantity, price) VALUES
(1, 14, 1, 250000); -- 1x Gundam Aerial