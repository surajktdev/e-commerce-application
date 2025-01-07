package com.ecommerce.repository;

import com.ecommerce.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
