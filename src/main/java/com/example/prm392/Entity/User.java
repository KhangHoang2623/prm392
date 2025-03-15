package com.example.prm392.Entity;


import com.example.prm392.Entity.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractAuditingEntity<String> {

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.CUSTOMER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();
}
