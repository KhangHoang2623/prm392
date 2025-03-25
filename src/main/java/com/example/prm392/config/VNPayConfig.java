package com.example.prm392.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class VNPayConfig {
    @Value("${vnpay.tmn-code}")
    private String tmnCode;

    @Value("${payment.vnpay.secret-key}")
    private String secretKey;

}
