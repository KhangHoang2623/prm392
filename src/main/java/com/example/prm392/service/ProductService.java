package com.example.prm392.service;

import com.example.prm392.Entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity getProductById(String id);
    List<ProductEntity> getAllProduct();
}
