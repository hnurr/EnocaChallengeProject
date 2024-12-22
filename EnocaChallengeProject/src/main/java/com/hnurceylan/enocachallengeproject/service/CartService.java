package com.hnurceylan.enocachallengeproject.service;


import com.hnurceylan.enocachallengeproject.dto.AddProductDto;
import com.hnurceylan.enocachallengeproject.dto.RemoveProductFromCartDto;
import com.hnurceylan.enocachallengeproject.entity.Cart;
import com.hnurceylan.enocachallengeproject.entity.CartProductDetails;
import com.hnurceylan.enocachallengeproject.entity.Product;
import com.hnurceylan.enocachallengeproject.repository.CartProductDetailsRepository;
import com.hnurceylan.enocachallengeproject.repository.CartRepository;
import com.hnurceylan.enocachallengeproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductDetailsRepository cartProductDetailsRepository;

    public ResponseEntity<?> getCart(int id) {

        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isPresent()) {

            List<CartProductDetails> cartDetails = cartProductDetailsRepository.findByCartId(id);

            // Sepet detaylarını direkt döndür
            return ResponseEntity.ok(cartDetails);
        } else {
            return ResponseEntity.ok("The cart with this id was not found.");
        }
    }

    public Cart getCart2(int id) {

        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isPresent()) {
            return cart.get();
        } else {
            return null;

        }

    }

    public ResponseEntity<?> updateCart(int id, Cart cart) {

        if (cartRepository.findById(id).isPresent()) {

            Cart cart1 = cartRepository.findById(id).get();

            if (cart != null) {


                cart1.setCustomer(cart.getCustomer());
                //cart1.setProducts(cart.getProducts());
                cart1.setCartProductDetails(cart.getCartProductDetails());
                cart1.setUpdatedAt(cart.getUpdatedAt());
                cart1.setTotalPrice(cart.getTotalPrice());


                Cart updatedCart = cartRepository.save(cart1);

                return ResponseEntity.ok(updatedCart);

            } else {
                String message = "cart could not be update.";
                return ResponseEntity.ok(message);
            }
        } else {

            String message = "cart could not be found in database.";
            return ResponseEntity.ok(message);
        }
    }




    public ResponseEntity<String> emptyCart(int id) {

        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isPresent()) {

            ProductService productService = new ProductService();


            for (CartProductDetails cartProductDetails : cart.get().getCartProductDetails()) {

                cartProductDetails.setQuantity(0);
            }

            cart.get().setTotalPrice(0);
            cartRepository.save(cart.get());
            String message = id + " Item number has been emptied successfully";
            return ResponseEntity.ok(message);
        } else {
            String message = "cart not found";
            return ResponseEntity.ok(message);
        }

    }


    public ResponseEntity<?> addProductToCart(AddProductDto request) {

        int cartId = request.getCartId();
        int productId = request.getProductId();
        int quantity = request.getQuantity();

        Object objectCartId = request.getCartId();
        Object objectProductId = request.getCartId();
        Object objectQuantity = request.getCartId();

        if (!(objectCartId instanceof Integer) || !(objectProductId instanceof Integer) || !(objectQuantity instanceof Integer)) {
            String message = "some field/fields  is not a integer";
            return ResponseEntity.ok(message);
        }

        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (!cartOptional.isPresent()) {
            String message = "Cart not found";
            return ResponseEntity.ok(message);
        }


        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            String message = "Product not found";
            return ResponseEntity.ok(message);
        }

        Product product = productOptional.get();
        Cart cart = cartOptional.get();

        // Stok kontrolü
        if (product.getStock() < quantity) {
            String message = "Not enough stock";
            return ResponseEntity.ok(message);
        }


        Optional<CartProductDetails> existingCartProduct = cartProductDetailsRepository.findByCartAndProduct(cart, product);

        if (existingCartProduct.isPresent()) {
            CartProductDetails cartProductDetails = existingCartProduct.get();
            cartProductDetails.setQuantity(cartProductDetails.getQuantity() + quantity);
            cartProductDetailsRepository.save(cartProductDetails);
        } else {
            CartProductDetails cartProductDetails = new CartProductDetails();
            cartProductDetails.setProduct(product);
            cartProductDetails.setCart(cart);
            cartProductDetails.setQuantity(quantity);
            cartProductDetailsRepository.save(cartProductDetails);

            cart.getCartProductDetails().add(cartProductDetails);
        }


        product.setStock(product.getStock() - quantity);
        productRepository.save(product);


        cartRepository.save(cart);

        return ResponseEntity.ok("Product added to cart successfully");
    }


    public ResponseEntity<?> removeProductFromCart(RemoveProductFromCartDto removeProductFromCartDto) {


        int cartId = removeProductFromCartDto.getCartId();
        int productId = removeProductFromCartDto.getProductId();


        Optional<Cart> cart = cartRepository.findById(cartId);

        if (!cart.isPresent()) {

            String message = "No cart was found with this ID.";
            return ResponseEntity.ok(message);

        }
        Cart cart1 = cart.get();

        Optional<CartProductDetails> cartProductDetails = cartProductDetailsRepository
                .findByCartAndProduct(cart1, productRepository.findById(productId).orElse(null));

        if (!cartProductDetails.isPresent()) {
            String message = "The product was not found in the cart.";
            return ResponseEntity.ok(message);
        }

        CartProductDetails cartProductDetails1 = cartProductDetails.get();


        cartProductDetailsRepository.delete(cartProductDetails1);


        String message = "The product has been successfully deleted from the cart.";
        return ResponseEntity.ok(message);


    }
}