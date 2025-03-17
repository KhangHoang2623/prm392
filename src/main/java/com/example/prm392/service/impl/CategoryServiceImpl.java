package com.example.prm392.service.impl;


import com.example.prm392.Entity.CategoryEntity;
import com.example.prm392.Entity.ProductEntity;
import com.example.prm392.dto.response.ProductGeneralResponse;
import com.example.prm392.mapper.ProductMapper;
import com.example.prm392.repositoriy.CategoryRepository;
import com.example.prm392.service.CategoryService;
import com.example.prm392.web.error.ExceptionDefine.NotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductGeneralResponse> getProductByCategory(@NonNull String categoryId) {
            CategoryEntity category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Id not found"));

            List<ProductEntity> products = category.getProducts();
            return productMapper.toDto(products);
    }
}
