package com.cache.demo.CacheCommerce.reposictory.impl;

import com.cache.demo.CacheCommerce.entities.Product;
import com.cache.demo.CacheCommerce.reposictory.ProductsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ProductRepoImpl {

    private final ProductsRepo repo;

    public ProductRepoImpl(ProductsRepo repo) {
        this.repo = repo;
    }

    public List<Product> getAllProd() {
        log.info("Retrieving all products from Database..... ");
        return repo.findAll();
    }

    public Product getProdById(int id) {
        log.info("Retrieving a product info from Database..... ");
        return repo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }

    public List<Product> getProdByCategoryName(String categoryName) {
        log.info("Retrieving products info from Database..... ");

        List<Product> productsByCategorylist = repo.findAll()
                .stream()
                .filter(product -> product.getCategory().equals(categoryName)).toList();

        if (productsByCategorylist.isEmpty())
            throw new RuntimeException("Category not found");

        return productsByCategorylist;

    }

    public Product createProduct(Product product) {
        log.info("Creating new product entry in Database......");
        return repo.save(product);
    }

    public Product updateProd(int id) {
        log.info("Updating product info in DB.........");

        Product existingProd = repo.findById(id).orElseThrow(() -> new RuntimeException("product not found in DB...."));
        existingProd.setPrice(new Random().nextDouble(200,700));

        return repo.save(existingProd);
    }

    public String deleteProd(int id){
        log.info("Deleting the product from info");
        repo.deleteById(id);

        return "Successfully deleted a product";
    }


}
