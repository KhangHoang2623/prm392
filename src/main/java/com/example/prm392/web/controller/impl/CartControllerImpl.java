package com.example.prm392.web.controller.impl;

import com.example.prm392.Entity.Enum.QuantityAction;
import com.example.prm392.dto.request.AddToCartRequest;
import com.example.prm392.dto.response.Response;
import com.example.prm392.service.CartService;
import com.example.prm392.web.controller.CartController;
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
@Tag(name = "CART API", description = "APIs for cart")
public class CartControllerImpl implements CartController {

    private final CartService cartService;

    @Override
    @Operation(summary = "Thêm 1 product vào giỏ hàng cùng với số lượng của nó ",
            description = "Trả về successfully nếu thêm thành công")
    public Response<?> addToCart(String cartId, AddToCartRequest addToCartRequest) {
        return Response.created(cartService.addToCart(cartId, addToCartRequest));
    }

    @Override
    @Operation(summary = "Show tất cả giỏ hàng cart item trong giỏ hàng đó  ",
            description = "Yêu cầu user đã đăng nhập, trả về tất cả giỏ hàng của User bao gồm : hình ảnh của product," +
                    "tên, tổng giá của giỏ hàng, giá trên từng cart item + số lượng")
    public Response<?> getAllCart() {
        return Response.ok(cartService.getAllCartOfAUser());
    }

    @Override
    @Operation(summary = "Xóa 1 CartItem khỏi giỏ hàng  ",
            description = "Xóa 1 cart item đó khỏi giỏ hàng, trả về 204 nếu thành công, vì mỗi cart item " +
                    "chỉ thuộc về 1 cart nên không cần lo")
    public Response<?> removeCartItemInACart(String cartItemId) {
        cartService.removeCartItemInACart(cartItemId);
        return Response.noContent();
    }

    @Override
    @Operation(summary = "Xóa tất cả CartItem khỏi giỏ hàng  ",
            description = "Xóa tất cả cart item khỏi giỏ hàng, trả về 204 nếu thành công")
    public Response<?> removeEntireCart(String cartId) {
        cartService.removeEntireCart(cartId);
        return Response.noContent();
    }

    @Override
    @Operation(summary = "Update số lượng CartItem trong giỏ hàng  ",
            description = "Truyền vào action (INCREASE or DECREASE) cùng với số lượng và id của cartitem")
    public Response<?> updateCartItem(String cartItemId, int quantity, QuantityAction action) {
        return Response.ok(cartService.updateQuantityOfCartItem(action, quantity, cartItemId));
    }


}
