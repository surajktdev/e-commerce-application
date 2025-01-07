package com.ecommerce.auth;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String email; // username
	private String password;
}
