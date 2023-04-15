package com.aptech.blog.config;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiJwtAuthorizationFilter extends BasicAuthenticationFilter {

    public ApiJwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
       
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException { 

                
        super.doFilterInternal(request, response, chain);
    }
    
}
