package com.ecommerce.exceptions;

import lombok.Builder;

@Builder
public record ApiResponse(String message, boolean success) {}
