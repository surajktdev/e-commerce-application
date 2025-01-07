package com.ecommerce.module.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse(Long cartId, Long memberId, Double totalPrice, List<CartItemResponse> items) {
    public record CartItemResponse(Long productId, String productName, Double productPrice, Integer quantity, Double totalPrice){}
}
