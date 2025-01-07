package com.ecommerce.module.user.value;

import com.ecommerce.role.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(Long id, Long memberId,String name, String email, String password, Role role, String status) {
}
