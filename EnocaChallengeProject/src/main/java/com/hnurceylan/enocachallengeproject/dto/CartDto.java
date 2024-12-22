package com.hnurceylan.enocachallengeproject.dto;

import lombok.Data;

@Data
public class CartDto {
    private Long id;
    private String productName;
    private Long customerId; // Sadece müşteri ID'sini ekle

    // Constructor, Getter ve Setter

    public CartDto(Long id, String productName, Long customerId) {
        this.id = id;
        this.productName = productName;
        this.customerId = customerId;
    }
}

