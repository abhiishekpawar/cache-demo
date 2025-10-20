package com.cache.demo.CacheCommerce.controller;

import com.cache.demo.CacheCommerce.entities.Product;
import com.cache.demo.CacheCommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.retrieveAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.retrieveProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/category/{name}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String name) {
        List<Product> products = service.retrieveProductByCategory(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProd(@RequestBody Product product) {
        Product newProd = service.createNewProd(product);
        return new ResponseEntity<>(newProd, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProd(@PathVariable int id) {
        Product newProd = service.updateProd(id);
        return new ResponseEntity<>(newProd, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        String msg = service.deleteProd(id);

        return new ResponseEntity<>(msg,HttpStatus.OK);
    }


}
