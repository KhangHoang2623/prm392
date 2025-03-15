package com.example.prm392.config.filter;

import com.example.prm392.config.SecurityConstants;
import com.example.prm392.config.SecurityProperties;
import com.example.prm392.repositoriy.AccountRepository;
import com.example.prm392.utils.JwtUtils;
import com.example.prm392.web.error.MessageCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter extends OncePerRequestFilter {
    SecurityProperties securityProperties;
    AccountRepository accountRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        boolean isPublic = SecurityConstants.PUBLIC_URIS.stream()
                .anyMatch(pattern -> requestURI.matches(pattern.replace("*", ".*")));

        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Is public: " + isPublic);

        if (isPublic) {
            filterChain.doFilter(request, response);
        }else{
            final var authentication = getAuthentication(request, response);
            if (!ObjectUtils.isEmpty(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }else{
                responseFailCredential(response, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var authorization = request.getHeader("Authorization");
        UsernamePasswordAuthenticationToken authentication = null;

        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            final var token = authorization.substring(7);
            try{
                JwtUtils.validateJwtToken(token, securityProperties.getJwtSecret()); //check token match voi signature
            }catch (Exception ex){
                responseFailCredential(response, HttpStatus.UNAUTHORIZED);
                return null;
            }
            final var uuid = JwtUtils.getUserUuidFromJWTToken(token, securityProperties.getJwtSecret());
            final var account = this.accountRepository.findById(uuid)
                    .orElseThrow(EntityNotFoundException::new);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + account.getRole().name());
            authentication = new UsernamePasswordAuthenticationToken(account, null, Collections.singletonList(authority) );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }
        return authentication;

    }

    private void responseFailCredential(HttpServletResponse response, HttpStatus status) throws IOException{
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        new ObjectMapper()
                .writeValue(response.getOutputStream(), new MessageCode(String.valueOf(status.value()), status.getReasonPhrase()));
        response.flushBuffer();
    }

}