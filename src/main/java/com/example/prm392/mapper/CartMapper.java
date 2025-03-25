package com.example.prm392.mapper;


import com.example.prm392.Entity.Cart;
import com.example.prm392.Entity.CartItemEntity;
import com.example.prm392.Entity.ProductEntity;
import com.example.prm392.dto.response.CartItemResponse;
import com.example.prm392.dto.response.CartResponse;
import com.example.prm392.dto.response.ProductDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        config = MapperConfig.class
)

public interface CartMapper extends EntityMapper<CartResponse, Cart> {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "imageUrl", source = "imageUrl")
    ProductDTOResponse toProductDTO(ProductEntity product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "product", source = "product")
    CartItemResponse toCartItemDTO(CartItemEntity cartItem);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cartItems", source = "cartItems")
    List<CartResponse> toCartDTO(List<Cart> cart);


}
