package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT MAX(u.member_id) FROM User u")
    Long findMemberId();

    @Query(nativeQuery = true, value = "SELECT * FROM User u WHERE u.member_id  = :memberId")
    User findByMemberId(Long memberId);
}
