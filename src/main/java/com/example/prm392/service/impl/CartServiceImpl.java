package com.example.prm392.service.impl;

import com.example.prm392.Entity.*;
import com.example.prm392.Entity.Enum.CartStatus;
import com.example.prm392.Entity.Enum.QuantityAction;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.CartResponse;
import com.example.prm392.mapper.CartMapper;
import com.example.prm392.repositoriy.*;
import com.example.prm392.service.CartService;
import com.example.prm392.utils.ContextHolderUtils;
import com.example.prm392.utils.VNPayUtil;
import com.example.prm392.web.error.ExceptionDefine.FailedException;
import com.example.prm392.web.error.ExceptionDefine.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {
    CartRepository _cartRepository;
    CartItemRepository _cartItemRepository;
    ProductRepository productRepository;
    CartMapper _cartMapper;
    OrderRepository _oOrderRepository;

    @Transactional
    @Override
    public String addToCart(String cartId, AddToCartRequest addToCartRequest) {

        try{
            Cart existingCart= _cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()
                    , "Cart not found"));

            ProductEntity product = productRepository.findById(addToCartRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Product not found"));

            CartItemEntity existingCartItem = existingCart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getId().equals(addToCartRequest.getProductId()))
                    .findFirst()
                    .orElse(null);

            if(existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + addToCartRequest.getQuantity());
                _cartItemRepository.save(existingCartItem);
            }else {
                CartItemEntity newCartItem = CartItemEntity.builder()
                        .cart(existingCart)
                        .product(product)
                        .quantity(addToCartRequest.getQuantity())
                        .build();
                existingCart.getCartItems().add(newCartItem);
            }
            existingCart.calculateTotalPrice();
            _cartRepository.save(existingCart);
            return "Product added to cart successfully!";
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public List<CartResponse> getAllCartOfAUser() {
                User getCurrentUser = ContextHolderUtils.getContext();
                List<Cart> cart = getCurrentUser.getCarts();
                return _cartMapper.toCartDTO(cart);
    }

    @Override
    @Transactional
    public void removeCartItemInACart(String cartItemId) {
        CartItemEntity cartItemEntity = _cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "CartItem Not Found")
        );
        Cart cart = cartItemEntity.getCart();
        cart.getCartItems().remove(cartItemEntity);
        cart.calculateTotalPrice();
    }

    @Override
    @Transactional
    public void removeEntireCart(String cartId) {
        Cart existingCart= _cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()
                , "Cart not found"));

        existingCart.getCartItems().clear();
        existingCart.calculateTotalPrice();

    }

    @Override
    @Transactional
    public String updateQuantityOfCartItem(QuantityAction action, int quantity, String cartItemId) {
        try{
            CartItemEntity cartItemEntity = _cartItemRepository.findById(cartItemId).orElseThrow(
                    () -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "CartItem Not Found")
            );
            Cart currentlyCart = cartItemEntity.getCart();
            if (action == QuantityAction.INCREASE) {
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
            } else {
                if (cartItemEntity.getQuantity() - quantity <= 0) {
                    removeCartItemInACart(cartItemId);
                } else {
                    cartItemEntity.setQuantity(cartItemEntity.getQuantity() - quantity);
                }
            }

            currentlyCart.calculateTotalPrice();
            return "Successfully updated cart item!";
        }catch (Exception ex){
            return "Update Failed";
        }

    }

    @Override
    public CartResponse createACart() {
        User getCurrentUser = ContextHolderUtils.getContext();
        Cart newCart = Cart.builder()
                .cartItems(new ArrayList<>())
                .user(getCurrentUser)
                .build();
        _cartRepository.save(newCart);
        return _cartMapper.toDto(newCart);
    }

    @Override
    public String payout(String cartId) throws UnsupportedEncodingException {
        Cart existingCart= _cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()
                , "Cart not found"));

        return VNPayUtil.createPaymentUrl(existingCart.getId(), existingCart.getPrice());
    }

    @Transactional
    @Override
    public String orderSuccess(String vn_txnRef, String responseCode) {
        Cart existingCart= _cartRepository.findById(vn_txnRef).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()
                , "Cart not found"));
        User user = existingCart.getUser();

        if (Objects.equals(responseCode, "00") || Objects.equals(responseCode, "02")){
            OrderEntity newOrderEntity = OrderEntity.builder()
                    .cart(existingCart)
                    .user(user)
                    .build();

            existingCart.setStatus(CartStatus.COMPLETED);
            _cartRepository.save(existingCart);
            _oOrderRepository.save(newOrderEntity);
            return "Order Completed";
        }else{
            return "Failed To Confirm Order";
        }
    }
}
