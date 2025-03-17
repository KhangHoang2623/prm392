package com.example.prm392.service.impl;

import com.example.prm392.Entity.ProductEntity;
import com.example.prm392.repositoriy.ProductRepository;
import com.example.prm392.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;


    @Override
    public ProductEntity getProductById(@NonNull String id) {
        return productRepository.findById(id) .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Id not found"));
    }

    @Override
    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }
}
