package com.example.prm392.web.controller;


import com.example.prm392.dto.request.LoginRegisterRequest;
import com.example.prm392.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
public interface CategoryController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Response<?> getAllCategory();

    @GetMapping("/{categoryId}/product")
    @ResponseStatus(HttpStatus.OK)
    Response<?> getProductByCategoryId(@PathVariable("categoryId") final String categoryId);
}
