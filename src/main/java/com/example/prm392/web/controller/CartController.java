package com.example.prm392.web.controller;


import com.example.prm392.Entity.Enum.QuantityAction;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.Response;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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


    @PostMapping("/createACart/")
    @ResponseStatus(HttpStatus.CREATED)
    Response<?> createACart();

    @PostMapping("/payout")
    @ResponseStatus(HttpStatus.OK)
    Response<?> payout(@RequestParam String cartItemId) throws UnsupportedEncodingException;

    @GetMapping("/orderSucess")
    @ResponseStatus(HttpStatus.OK)
    Response<?> orderSucces(@RequestParam String vnp_TxnRef,
                            @RequestParam String vnp_ResponseCode);
}
