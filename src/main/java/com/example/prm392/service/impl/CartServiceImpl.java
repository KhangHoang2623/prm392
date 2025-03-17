package com.example.prm392.service.impl;

import com.example.prm392.Entity.Cart;
import com.example.prm392.Entity.CartItemEntity;
import com.example.prm392.Entity.Enum.CartStatus;
import com.example.prm392.Entity.ProductEntity;
import com.example.prm392.Entity.User;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.CartResponse;
import com.example.prm392.mapper.CartMapper;
import com.example.prm392.repositoriy.CartItemRepository;
import com.example.prm392.repositoriy.CartRepository;
import com.example.prm392.repositoriy.ProductRepository;
import com.example.prm392.service.CartService;
import com.example.prm392.utils.ContextHolderUtils;
import com.example.prm392.web.error.ExceptionDefine.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {
    CartRepository _cartRepository;
    CartItemRepository _cartItemRepository;
    ProductRepository productRepository;
    CartMapper _cartMapper;

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
                return _cartMapper.toDto(cart);
    }
}
