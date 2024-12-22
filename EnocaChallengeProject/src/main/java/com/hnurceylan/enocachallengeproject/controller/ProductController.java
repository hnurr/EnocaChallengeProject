package com.hnurceylan.enocachallengeproject.controller;

import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id){
        return productService.getProduct(id);

    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct( @RequestBody Product product) {
        Product savedProduct = productService.createProduct(product).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id,product);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return  productService.deleteProduct(id);
    }
}
