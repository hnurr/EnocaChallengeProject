package com.hnurceylan.enocachallengeproject;


import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.repository.ProductRepository;
import com.hnurceylan.enocachallengeproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetProductTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        product = new Product();
        product.setId(1L);
        product.setName("Test Product");

    }

    @Test
    public void testGetProductFound() {

        when(productRepository.findById(1)).thenReturn(Optional.of(product));


        ResponseEntity<?> response = productService.getProduct(1);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductNotFound() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());


        ResponseEntity<?> response = productService.getProduct(1);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product with ID 1 not found.", response.getBody());
    }
}
