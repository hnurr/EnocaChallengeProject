package com.hnurceylan.enocachallengeproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends Base {

    private String name;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public Product( String name, Double price) {

        this.name = name;
        this.price = price;
    }

    public Product(){}


    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock= stock;
    }
}
