package com.example.prm392.web.controller.impl;

import com.example.prm392.dto.response.Response;
import com.example.prm392.service.ProductService;
import com.example.prm392.web.controller.ProductController;
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
@Tag(name = "PRODUCT API", description = "APIs for product")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Operation(summary = "Lấy danh sách tất cả sản phẩm", description = "API này trả về danh sách toàn bộ sản phẩm có trong hệ thống.")
    @Override
    public Response<?> getAllProduct() {
        return Response.ok(productService.getAllProduct());
    }

    @Operation(summary = "Lấy thông tin chi tiết sản phẩm", description = "API này trả về thông tin chi tiết của một sản phẩm dựa trên ID sản phẩm.")
    @Override
    public Response<?> getProductById(String productId) {
        return Response.ok(productService.getProductById(productId));
    }
}
