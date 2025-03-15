package com.example.prm392.Entity;


import com.example.prm392.Entity.Enum.PaymentStatus;
import com.example.prm392.Entity.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Payment")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends AbstractAuditingEntity<String> {

    @Column(name = "amount")
    int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;


}
