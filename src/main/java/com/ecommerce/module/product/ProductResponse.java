package com.ecommerce.module.product;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.User;
import com.ecommerce.module.user.value.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductResponse(Long id, String name, String description, Double price, Integer quantity, UserResponse seller, Category category_id) {
}
