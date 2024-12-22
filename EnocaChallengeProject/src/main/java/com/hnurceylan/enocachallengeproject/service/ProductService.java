package com.hnurceylan.enocachallengeproject.service;


import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ResponseEntity<?> getProduct(int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {

            String message = "Product with ID " + id + " not found.";
            return ResponseEntity.ok(message);
        }
    }


    public ResponseEntity<Product> createProduct(Product product) {

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());


        productRepository.save(newProduct);


        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

    }

    public ResponseEntity<?> updateProduct(int id, Product product) {
        if (productRepository.findById(id).isPresent()) {
            Product product1 = productRepository.findById(id).get();
            if (product != null) {

                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                product1.setStock(product.getStock());
                product1.setUpdatedAt(product.getUpdatedAt());

                Product updatedProduct = productRepository.save(product1);


                return ResponseEntity.ok(updatedProduct);
            } else {
                String message = "product could not be update.";
                return ResponseEntity.ok(message);
            }
        } else {

            String message = "product could not be found in database.";
            return ResponseEntity.ok(message);
        }

    }

    public ResponseEntity<String> deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.delete(product.get());
            String message = id + " Item number has been deleted successfully";
            return ResponseEntity.ok(message);
        } else {
            String message = "product not found";
            return ResponseEntity.ok(message);
        }
    }

}
