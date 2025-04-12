package com.example.prm392.service.impl;


import com.example.prm392.Entity.Cart;
import com.example.prm392.Entity.CartItemEntity;
import com.example.prm392.Entity.Enum.CartStatus;
import com.example.prm392.Entity.Enum.UserRole;
import com.example.prm392.Entity.User;
import com.example.prm392.config.SecurityProperties;
import com.example.prm392.dto.request.LoginRegisterRequest;
import com.example.prm392.dto.response.LoginResponse;
import com.example.prm392.repositoriy.AccountRepository;
import com.example.prm392.repositoriy.CartRepository;
import com.example.prm392.service.AuthenticationService;
import com.example.prm392.utils.JwtUtils;
import com.example.prm392.web.error.ExceptionDefine.AuthenticationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {

    AccountRepository accountRepository;
    CartRepository cartRepository;
    PasswordEncoder passwordEncoder;
    SecurityProperties securityProperties;

    @Override
    @Transactional
    public User register(LoginRegisterRequest request) {
        if(accountRepository.findByUsername(request.getUsername()).isPresent()){
            throw new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Username already exists");
        }
        final var newAccount = User.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.CUSTOMER)
                .build();

        Cart newCart = Cart.builder()
                .user(newAccount)
                .cartItems(new ArrayList<>())
                .status(CartStatus.ACTIVE)
                .build();


        accountRepository.save(newAccount);
        cartRepository.save(newCart);
        return newAccount;
    }


    @Override
    public LoginResponse login(LoginRegisterRequest request) {
        final var account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Email not found"));

        if(!passwordEncoder.matches(request.getPassword(), account.getPasswordHash())){
            throw new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Your information does not match our password");
        }

        final var token = JwtUtils.generateJwtToken(account, securityProperties.getJwtSecret(), securityProperties.getJwtExpiration());
        return new LoginResponse(token);

    }
}
