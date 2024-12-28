package com.doslab.human_detector;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Определяем целевую страницу в зависимости от роли
        String redirectUrl = determineTargetUrl(authentication);
        response.sendRedirect(redirectUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse("");
    
        switch (role) {
            case "ROLE_ADMIN":
                return "/admin/profile";
            case "ROLE_USER":
                return "/user/profile";
            default:
                return "/"; // Default URL
        }
    }

}