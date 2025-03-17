package com.example.prm392.service;

import com.example.prm392.Entity.Cart;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    String addToCart(String cartId, AddToCartRequest addToCartRequest);
    List<CartResponse> getAllCartOfAUser();
}
