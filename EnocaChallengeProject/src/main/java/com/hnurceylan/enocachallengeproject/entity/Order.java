package com.hnurceylan.enocachallengeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Base {

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> products;

    String orderCode;




    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProductDetails> orderDetails; // Fiyat geçmişi

    double totalPrice;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<OrderProductDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderProductDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
