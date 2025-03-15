package com.example.prm392.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends AbstractAuditingEntity<String>{

    @Column(name = "product_name", nullable = false)
    String name;

    @Column(name = "brief_description")
    String briefDescription;

    @Column(name = "full_description", columnDefinition = "TEXT")
    String fullDescription;

    @Column(name = "technical", columnDefinition = "TEXT")
    String technicalDescription;

    @Column(name = "price")
    Double price;

    @Column(name = "image_url", columnDefinition = "TEXT")
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
