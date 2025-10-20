package com.cache.demo.CacheCommerce.service;

import com.cache.demo.CacheCommerce.entities.Product;
import com.cache.demo.CacheCommerce.reposictory.impl.ProductRepoImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepoImpl repo;

    public ProductService(ProductRepoImpl repo) {
        this.repo = repo;
    }

    @Cacheable(cacheNames = "all_products")
    public List<Product> retrieveAllProducts() {
        return repo.getAllProd();
    }

    @Cacheable(cacheNames = "products", key = "#id")
    public Product retrieveProduct(int id) {
        return repo.getProdById(id);
    }

    public List<Product> retrieveProductByCategory(String category) {
        return repo.getProdByCategoryName(category);
    }

    public Product createNewProd(Product product) {
        return repo.createProduct(product);
    }

    @CachePut(cacheNames = "products", key = "#id")
    public Product updateProd(int id) {
        return repo.updateProd(id);
    }

    @CacheEvict(cacheNames = "products", key = "#id")
    public String deleteProd(int id) {
        return repo.deleteProd(id);
    }
}
