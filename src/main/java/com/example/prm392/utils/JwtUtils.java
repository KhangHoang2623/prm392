package com.example.prm392.utils;



import com.example.prm392.Entity.User;
import com.example.prm392.web.error.ExceptionDefine.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {


    public static String generateJwtToken(User account, String jwtSecret, int jwtExpiration) {
        return Jwts.builder()
                .setSubject(account.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String getUserUuidFromJWTToken(String token, String jwtSecret){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public static void validateJwtToken(String token, String jwtSecret){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        }catch (Exception e){
            throw new AuthenticationException("Invalid token");
        }
    }
}
