package com.example.prm392.service;

import com.example.prm392.Entity.CategoryEntity;
import com.example.prm392.dto.response.ProductGeneralResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategories();
    List<ProductGeneralResponse> getProductByCategory(String categoryId);
}
