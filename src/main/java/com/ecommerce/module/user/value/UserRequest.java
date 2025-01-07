package com.ecommerce.module.user.value;

import com.ecommerce.role.Role;
import lombok.Builder;

@Builder
public record UserRequest(String name, String email, String password, Role role) {
}
