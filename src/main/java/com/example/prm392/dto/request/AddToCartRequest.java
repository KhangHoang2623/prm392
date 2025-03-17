package com.example.prm392.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AddToCartRequest {
    @NotBlank(message = "Product Id cannot be Empty")
    String productId;

    int quantity=1;
}
