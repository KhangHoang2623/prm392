package com.example.prm392.mapper;

import com.example.prm392.Entity.ProductEntity;
import com.example.prm392.dto.response.ProductGeneralResponse;
import org.mapstruct.Mapper;

@Mapper(
        config = MapperConfig.class
)
public interface ProductMapper extends EntityMapper<ProductGeneralResponse, ProductEntity> {
}
