package com.example.prm392.Entity;


import com.example.prm392.Entity.Enum.OrderStatus;
import com.example.prm392.Entity.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends AbstractAuditingEntity<String> {



    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Column(name = "order_date", updatable = false)
    @Builder.Default
    private Instant oderDate = Instant.now();

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity payment;
}
