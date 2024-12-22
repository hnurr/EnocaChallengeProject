package com.hnurceylan.enocachallengeproject.controller;

import com.hnurceylan.enocachallengeproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder/{cartId}")
    public ResponseEntity<?> placeOrder(@PathVariable int cartId){
        return orderService.placeOrder(cartId);

    }

    @GetMapping("/getOrderForCode/{orderCode}")
    public ResponseEntity<?> getOrderForCode(@PathVariable String orderCode){
        return orderService.getOrderForCode(orderCode);
    }


    @GetMapping("/getAllOrderForCustomer/{customerId}")
    public ResponseEntity<?> getAllOrderForCustomer(@PathVariable int customerId){
        return orderService.getAllOrderForCustomer(customerId);
    }
}
