package com.example.prm392.repositoriy;

import com.example.prm392.Entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, String> {
}
