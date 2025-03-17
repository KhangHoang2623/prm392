package com.example.prm392.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<ProductEntity> products = new ArrayList<>();
}
