package com.ecommerce.repository;

import com.ecommerce.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByMemberId(Long memberId);
}
