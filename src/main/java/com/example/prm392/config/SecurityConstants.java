package com.example.prm392.config;

import java.util.List;


public class SecurityConstants {
    public static final List<String> PUBLIC_URIS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/verifyAccount",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/upload",
            "/api/category",
            "/api/category/.*/product",
            "/api/product/**",
            "/api/product",
            "/api/cart/orderSucess"
    );


}
