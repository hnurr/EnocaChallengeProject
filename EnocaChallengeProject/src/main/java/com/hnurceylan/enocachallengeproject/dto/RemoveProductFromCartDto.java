package com.hnurceylan.enocachallengeproject.dto;


import lombok.Data;

@Data
public class RemoveProductFromCartDto {

    private int cartId;
    private  int productId;

    public RemoveProductFromCartDto(int cartId, int productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
