package com.example.prm392.mapper;


import com.example.prm392.Entity.Cart;
import com.example.prm392.dto.response.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MapperConfig.class
)

public interface CartMapper extends EntityMapper<CartResponse, Cart> {

    @Mapping(source = "price", target = "price")
    @Mapping(source = "cartItems", target = "cartItems")
    CartResponse toDto(Cart cart);
}
