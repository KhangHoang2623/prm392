package com.example.prm392.web.controller;


import com.example.prm392.dto.response.Response;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/product")
public interface ProductController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Response<?> getAllProduct();


    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    Response<?> getProductById(@PathVariable final String productId);
}
