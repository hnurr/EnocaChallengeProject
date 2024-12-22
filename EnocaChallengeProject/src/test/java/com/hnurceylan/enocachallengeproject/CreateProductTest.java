package com.hnurceylan.enocachallengeproject;

import com.hnurceylan.enocachallengeproject.controller.ProductController;
import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateProductTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStock(50);
    }

    @Test
    public void testCreateProductSuccess() {

        when(productService.createProduct(any(Product.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(product));


        ResponseEntity<Product> response = productController.createProduct(product);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }



}
