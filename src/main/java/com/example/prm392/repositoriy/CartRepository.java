package com.example.prm392.repositoriy;

import com.example.prm392.Entity.Cart;
import com.example.prm392.Entity.Enum.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUserIdAndStatus(String userId, CartStatus status);
//    Optional<Cart> findCartBy(String userId, CartStatus status);
}
