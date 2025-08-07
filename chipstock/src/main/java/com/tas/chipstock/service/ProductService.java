package com.tas.chipstock.service;

import com.tas.chipstock.model.Product;
import com.tas.chipstock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @PostConstruct
    public void initSampleData() {
        // Create sample products if none exist
        if (productRepository.count() == 0) {
            createSampleProducts();
        }
    }
    
    private void createSampleProducts() {
        Product product1 = new Product("Intel Core i7-13700K", 
            "High-performance 16-core processor with Intel UHD Graphics 770. Perfect for gaming and content creation.", 
            new BigDecimal("409.99"), 25);
        product1.setCategory("Processors");
        product1.setImageUrl("https://images.unsplash.com/photo-1555617778-02518db0d3dc?w=400");
        
        Product product2 = new Product("NVIDIA GeForce RTX 4080", 
            "Flagship graphics card with 16GB GDDR6X memory. Delivers exceptional 4K gaming performance.", 
            new BigDecimal("1199.99"), 12);
        product2.setCategory("Graphics Cards");
        product2.setImageUrl("https://images.unsplash.com/photo-1591488320449-011701bb6704?w=400");
        
        Product product3 = new Product("Samsung 32GB DDR5-5600", 
            "High-speed DDR5 RAM kit with RGB lighting. Optimized for Intel 13th gen and AMD Ryzen 7000 series.", 
            new BigDecimal("159.99"), 45);
        product3.setCategory("Memory");
        product3.setImageUrl("https://images.unsplash.com/photo-1562976540-200bd077b7fd?w=400");
        
        Product product4 = new Product("Samsung 980 PRO 2TB NVMe SSD", 
            "Ultra-fast PCIe 4.0 NVMe SSD with read speeds up to 7,000 MB/s. Perfect for demanding applications.", 
            new BigDecimal("149.99"), 30);
        product4.setCategory("Storage");
        product4.setImageUrl("https://images.unsplash.com/photo-1597872200969-2b65d56bd16b?w=400");
        
        Product product5 = new Product("ASUS ROG Strix Z790-E", 
            "Premium ATX motherboard with Wi-Fi 6E, DDR5 support, and extensive connectivity options.", 
            new BigDecimal("429.99"), 18);
        product5.setCategory("Motherboards");
        product5.setImageUrl("https://images.unsplash.com/photo-1518717758536-85ae29035b6d?w=400");
        
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<Product> getAvailableProducts() {
        return productRepository.findAvailableProducts();
    }
    
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }
    
    public boolean isProductInStock(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isPresent() && product.get().getStockQuantity() > 0;
    }
}
