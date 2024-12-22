package com.hnurceylan.enocachallengeproject.controller;


import com.hnurceylan.enocachallengeproject.dto.AddProductDto;
import com.hnurceylan.enocachallengeproject.dto.RemoveProductFromCartDto;
import com.hnurceylan.enocachallengeproject.entity.Cart;
import com.hnurceylan.enocachallengeproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable int id) {

        return cartService.getCart(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCart(@PathVariable int id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);

    }

    @DeleteMapping("/empty/{id}")
    public ResponseEntity<String> emptyCart(@PathVariable int id) {
        return cartService.emptyCart(id);

    }

    @PostMapping("/addProductToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductDto request) {

        return cartService.addProductToCart(request);
    }

    @DeleteMapping("/removeProductFromCart")
    public ResponseEntity<?> removeProductFromCart(@RequestBody RemoveProductFromCartDto removeProductFromCartDto) {

        return cartService.removeProductFromCart(removeProductFromCartDto);

    }


}
