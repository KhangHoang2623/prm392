package com.example.prm392.service;


import com.example.prm392.Entity.User;
import com.example.prm392.dto.request.LoginRegisterRequest;
import com.example.prm392.dto.response.LoginResponse;

public interface AuthenticationService {

    User register(LoginRegisterRequest request);
    LoginResponse login(LoginRegisterRequest request);

}
