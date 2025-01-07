package com.ecommerce.module.cart;

import lombok.Builder;

@Builder
public record CartRequest(Long productId, Integer quantity) {
}
