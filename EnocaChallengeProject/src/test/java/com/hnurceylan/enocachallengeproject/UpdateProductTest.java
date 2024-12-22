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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UpdateProductTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product existingProduct;
    private Product updatedProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setPrice(100.0);
        existingProduct.setStock(50);

        updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(120.0);
        updatedProduct.setStock(40);
    }


    @Test
    public void testUpdateProduct_WhenProductDoesNotExist_ShouldReturnErrorMessage() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<?> response = productService.updateProduct(1, updatedProduct);


        assertEquals("product could not be found in database.", response.getBody());
    }

    @Test
    public void testUpdateProduct_WhenProductIsNull_ShouldReturnErrorMessage() {

        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));


        ResponseEntity<?> response = productService.updateProduct(1, null);


        assertEquals("product could not be update.", response.getBody());
    }
}

