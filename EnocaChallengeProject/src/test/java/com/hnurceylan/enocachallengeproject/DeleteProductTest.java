package com.hnurceylan.enocachallengeproject;

import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.repository.ProductRepository;
import com.hnurceylan.enocachallengeproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class DeleteProductTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
    }

    @Test
    public void testDeleteProductSuccess() {

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseEntity<String> response = productService.deleteProduct(1);


        assertEquals(200, response.getStatusCodeValue());


        assertEquals("1 Item number has been deleted successfully", response.getBody());


        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testDeleteProductNotFound() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = productService.deleteProduct(1);


        assertEquals(200, response.getStatusCodeValue());


        assertEquals("product not found", response.getBody());


        verify(productRepository, times(0)).delete(any(Product.class));
    }
}
