package com.hnurceylan.enocachallengeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart extends Base {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProductDetails> cartProductDetails;


    private double totalPrice;


    public Customer getCustomer() {
        return customer;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartProductDetails> getCartProductDetails() {
        return cartProductDetails;
    }

    public void setCartProductDetails(List<CartProductDetails> cartProductDetails) {
        this.cartProductDetails = cartProductDetails;
    }
}
