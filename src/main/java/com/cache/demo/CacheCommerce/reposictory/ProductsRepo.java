package com.cache.demo.CacheCommerce.reposictory;

import com.cache.demo.CacheCommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Integer> {
}
