package com.example.prm392.service;

import com.example.prm392.Entity.Cart;
import com.example.prm392.Entity.Enum.QuantityAction;
import com.example.prm392.Entity.OrderEntity;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.CartResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CartService {
    String addToCart(String cartId, AddToCartRequest addToCartRequest);
    List<CartResponse> getAllCartOfAUser();
    void removeCartItemInACart(String cartItemId);
    void removeEntireCart(String cartId);
    String updateQuantityOfCartItem(QuantityAction action, int quantity, String cartItemId);
    CartResponse createACart();
    String payout(String cartId) throws UnsupportedEncodingException;
    String orderSuccess (String vn_txnRef, String responseCode);
}
