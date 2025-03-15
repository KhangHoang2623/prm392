package com.example.prm392.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LoginRegisterRequest {
    @NotBlank(message = "Username cannot be empty")
    String username;
    @NotBlank(message = "Password cannot be empty")
    String password;
}
