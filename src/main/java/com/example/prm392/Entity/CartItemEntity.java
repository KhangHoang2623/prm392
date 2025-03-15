package com.example.prm392.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity extends AbstractAuditingEntity<String>{
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
}
