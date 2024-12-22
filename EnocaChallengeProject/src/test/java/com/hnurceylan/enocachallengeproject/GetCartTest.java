package com.hnurceylan.enocachallengeproject;

import com.hnurceylan.enocachallengeproject.entity.Cart;
import com.hnurceylan.enocachallengeproject.entity.CartProductDetails;
import com.hnurceylan.enocachallengeproject.repository.CartProductDetailsRepository;
import com.hnurceylan.enocachallengeproject.repository.CartRepository;
import com.hnurceylan.enocachallengeproject.service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetCartTest {


    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartProductDetailsRepository cartProductDetailsRepository;

    @Test
    void testGetCart_CartExists() {

        Cart cart = new Cart();
        cart.setId(1L);


        CartProductDetails cartProductDetails = new CartProductDetails();
        cartProductDetails.setCart(cart);


        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));


        when(cartProductDetailsRepository.findByCartId(1)).thenReturn(List.of(cartProductDetails));


        ResponseEntity<?> response = cartService.getCart(1);


        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void testGetCart_CartDoesNotExist() {


        when(cartRepository.findById(99)).thenReturn(Optional.empty());


        ResponseEntity<?> response = cartService.getCart(99);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals("The cart with this id was not found.", response.getBody());
    }
}
