package com.ecommerce.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtAuthResponse {
	private String token;
	private String username;
}
