package com.ecommerce.module.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Operations", description = "Endpoints for handling cart-related functionalities")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;


    @GetMapping("/")
    @Operation(summary = "")
    public ResponseEntity<CartResponse> getCart(@RequestParam Long memberId){
      CartResponse response = service.getCart(memberId);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addItemToCart(@RequestParam Long userId, @RequestBody CartRequest cartRequest) {
        CartResponse response = service.addItemToCart(userId, cartRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<CartResponse> removeItemFromCart(@RequestParam Long userId, @PathVariable Long productId) {
        CartResponse response = service.removeItemFromCart(userId, productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartResponse> clearCart(@RequestParam Long userId) {
        CartResponse response = service.clearCart(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
