package com.ecommerce.module.cart;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;



    public CartResponse getCart(Long userId) {
        Cart cart = getOrCreateCartEntity(userId);
        return convertToCartResponse(cart);
    }

    @Transactional
    public CartResponse addItemToCart(Long userId, CartRequest cartRequest) {
        Cart cart = getOrCreateCartEntity(userId);

        Product product = productRepository.findById(Math.toIntExact(cartRequest.productId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartRequest.productId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartRequest.quantity());
            item.setPrice(product.getPrice() * item.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setCart(cart);
            newItem.setQuantity(cartRequest.quantity());
            newItem.setPrice(product.getPrice() * cartRequest.quantity());
            cart.getItems().add(newItem);
        }

        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        cartRepository.save(cart);

        return convertToCartResponse(cart);
    }

    @Transactional
    public CartResponse removeItemFromCart(Long userId, Long productId) {
        Cart cart = getOrCreateCartEntity(userId);

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItem::getPrice).sum());
        cartRepository.save(cart);

        return convertToCartResponse(cart);
    }

    @Transactional
    public CartResponse clearCart(Long userId) {
        Cart cart = getOrCreateCartEntity(userId);
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return convertToCartResponse(cart);
    }

    private Cart getOrCreateCartEntity(Long userId) {
        return cartRepository.findByMemberId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setMemberId(userId);
                    cart.setTotalPrice(0.0);
                    return cartRepository.save(cart);
                });
    }

    private CartResponse convertToCartResponse(Cart cart) {
        CartResponse.CartResponseBuilder response = CartResponse.builder().cartId(cart.getId()).memberId(cart.getMemberId()).totalPrice(cart.getTotalPrice());

        response.items(cart.getItems().stream().map(item -> new CartResponse.CartItemResponse(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getPrice(), item.getQuantity(), item.getPrice())).collect(Collectors.toList()));

        return response.build();
    }
}
