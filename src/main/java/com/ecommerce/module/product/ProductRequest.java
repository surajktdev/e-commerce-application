package com.ecommerce.module.product;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.User;
import lombok.Builder;

@Builder
public record ProductRequest(String name, String description, Double price, Integer quantity) {
}
