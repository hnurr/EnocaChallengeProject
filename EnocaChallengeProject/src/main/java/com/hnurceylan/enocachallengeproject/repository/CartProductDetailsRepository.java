package com.hnurceylan.enocachallengeproject.repository;


import com.hnurceylan.enocachallengeproject.entity.Cart;
import com.hnurceylan.enocachallengeproject.entity.CartProductDetails;
import com.hnurceylan.enocachallengeproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductDetailsRepository extends JpaRepository<CartProductDetails, Integer> {

    Optional<CartProductDetails> findByCartAndProduct(Cart cart, Product product);

    List<CartProductDetails> findByCartId(int id);

}
