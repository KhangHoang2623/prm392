package com.example.prm392.web.controller;


import com.example.prm392.Entity.Enum.QuantityAction;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.Response;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cart")
public interface CartController {

    @PostMapping("/addToCart")
    @ResponseStatus(HttpStatus.CREATED)
    Response<?> addToCart(@RequestParam String cartId,  @Valid @RequestBody final AddToCartRequest addToCartRequest);

    @GetMapping("/getAllCart")
    @ResponseStatus(HttpStatus.OK)
    Response<?> getAllCart();

    @DeleteMapping("/removeCartItem/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Response<?> removeCartItemInACart(@PathVariable String cartItemId);

    @DeleteMapping("/removeEntireCart/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Response<?> removeEntireCart(@PathVariable String cartId);

    @PutMapping("/updateCartItem/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    Response<?> updateCartItem(@PathVariable String cartItemId, final int quantity, final QuantityAction action);
}
