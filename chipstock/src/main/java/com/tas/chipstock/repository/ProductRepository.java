package com.tas.chipstock.repository;

import com.tas.chipstock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findByCategory(String category);
    
    List<Product> findByStockQuantityGreaterThan(Integer quantity);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0 ORDER BY p.createdAt DESC")
    List<Product> findAvailableProducts();
    
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL")
    List<String> findDistinctCategories();
}
