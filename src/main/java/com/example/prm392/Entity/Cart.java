package com.example.prm392.Entity;


import com.example.prm392.Entity.Enum.CartStatus;
import com.example.prm392.Entity.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends AbstractAuditingEntity<String>{

    @Column(name = "price")
    Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CartStatus role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

}
