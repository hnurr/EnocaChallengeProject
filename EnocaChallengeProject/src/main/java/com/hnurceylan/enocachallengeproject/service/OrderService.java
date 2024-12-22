package com.hnurceylan.enocachallengeproject.service;

import com.hnurceylan.enocachallengeproject.entity.*;
import com.hnurceylan.enocachallengeproject.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {


    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderProductDetailsRepository orderProductDetailsRepository;
    private final CartService cartService;
    private final CustomerRepository customerRepository;


    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository, OrderProductDetailsRepository orderProductDetailsRepository, CartService cartService, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderProductDetailsRepository = orderProductDetailsRepository;
        this.cartService = cartService;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> placeOrder(int cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (!cartOpt.isEmpty()) {
            Cart cart1 = cartOpt.get();

            List<CartProductDetails> cartProductDetails = cart1.getCartProductDetails();
            double total = 0;
            for (CartProductDetails cartProductDetails1 : cartProductDetails) {
                Product product = cartProductDetails1.getProduct();

                if (product.getStock() <= 0 || product.getStock() < cartProductDetails1.getQuantity()) {
                    String message = "the product is out of stock";
                    return ResponseEntity.ok(message);
                }
                total += product.getPrice() * cartProductDetails1.getQuantity();

                OrderProductDetails orderProductDetails = new OrderProductDetails();
                orderProductDetails.setProductName(product.getName());
                orderProductDetails.setProductPrice(product.getPrice());
                //orderProductDetails.setQuantity(1);
                orderProductDetailsRepository.save(orderProductDetails);
            }
            Order order = new Order();
            order.setCustomer(cart1.getCustomer());
            order.setOrderCode(UUID.randomUUID().toString());
            order.setTotalPrice(total);
            orderRepository.save(order);

            cartService.emptyCart(cartId);
            return ResponseEntity.ok("Order placed successfully." + total);
        }

        String message = "Cart not found";
        return ResponseEntity.ok(message);
    }


    public ResponseEntity<?> getOrderForCode(String orderCode) {

        Optional<Order> order = orderRepository.findByOrderCode(orderCode);

        if (!order.isPresent()) {

            String message = "This order code is invalid";
            return ResponseEntity.ok(message);
        }

        return ResponseEntity.ok(order.get());


    }

    public ResponseEntity<?> getAllOrderForCustomer(int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!customer.isPresent()){

            String message ="No customer found for this id number";
            return ResponseEntity.ok(message);
        }


      List<Order> orders = orderRepository.findByCustomerId(customerId);

        if (orders.isEmpty()){

            String message ="No cart found for this customer.";
            return ResponseEntity.ok(message);
        }

        return ResponseEntity.ok(orders);
        

    }
}


