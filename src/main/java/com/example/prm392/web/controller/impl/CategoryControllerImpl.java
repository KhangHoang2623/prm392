package com.example.prm392.web.controller.impl;


import com.example.prm392.dto.response.Response;
import com.example.prm392.service.CategoryService;
import com.example.prm392.web.controller.CategoryController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "CATEGORY API", description = "APIs for category")
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Lấy danh sách tất cả danh mục", description = "API này trả về danh sách tất cả danh mục sản phẩm.")
    @Override
    public Response<?> getAllCategory() {
        log.info("=================Request For Get All Category : =================");
        return Response.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Lấy tổng quan thông tin của sản phẩm theo ID danh mục",
            description = "API này trả về danh sách sản phẩm thuộc danh mục cụ thể dựa trên ID danh mục.")
    @Override
    public Response<?> getProductByCategoryId(String categoryId) {
        log.info("=================Request For Get Product With Category {} : =================", categoryId);
        return Response.ok(categoryService.getProductByCategory(categoryId));
    }
}
