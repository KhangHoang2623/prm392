package com.example.prm392.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Category")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity<String>{

    @Column(name = "name", nullable = false)
    String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<>();
}
