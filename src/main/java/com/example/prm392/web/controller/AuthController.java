package com.example.prm392.web.controller;


import com.example.prm392.dto.request.LoginRegisterRequest;
import com.example.prm392.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/register")
    Response<?> register(@Valid @RequestBody LoginRegisterRequest registerRequestRequest);

    @PostMapping("/login")
    Response<?> login(@Valid @RequestBody LoginRegisterRequest loginRequest);


}
