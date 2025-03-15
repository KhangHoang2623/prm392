package com.example.prm392.web.controller.impl;


import com.example.prm392.Entity.User;
import com.example.prm392.dto.request.LoginRegisterRequest;
import com.example.prm392.dto.response.LoginResponse;
import com.example.prm392.dto.response.Response;
import com.example.prm392.service.AuthenticationService;
import com.example.prm392.web.controller.AuthController;
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
@Tag(name = "AUTH API", description = "APIs for authen and author")
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;


    @Operation(summary = "Register a new account", description = "Create a new account and also create an user profile for that account ")
    @Override
    public Response<User> register(LoginRegisterRequest registerRequest) {
        log.info("=================Request Register For User : {} =================", registerRequest.getUsername());

        return Response.created(authenticationService.register(registerRequest));
    }

    @Override
    @Operation(summary = "Login ", description = "Login and response the token")
    public Response<LoginResponse> login(LoginRegisterRequest loginRequest) {
        log.info("=================Request Login For User : {} =================", loginRequest.getUsername());
        return Response.ok(authenticationService.login(loginRequest));
    }
}
