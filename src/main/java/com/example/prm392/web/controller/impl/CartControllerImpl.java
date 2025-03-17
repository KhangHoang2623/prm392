package com.example.prm392.web.controller.impl;

import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.Response;
import com.example.prm392.service.CartService;
import com.example.prm392.web.controller.CartController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "CART API", description = "APIs for cart")
public class CartControllerImpl implements CartController {

    private final CartService cartService;

    @Override
    public Response<?> addToCart(String cartId, AddToCartRequest addToCartRequest) {
        return Response.created(cartService.addToCart(cartId, addToCartRequest));
    }

    @Override
    public Response<?> getAllCart() {
        return Response.created(cartService.getAllCartOfAUser());
    }


}
