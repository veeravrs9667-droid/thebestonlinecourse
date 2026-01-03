package com.example.ocm.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    // ✅ Skip OPTIONS
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        filterChain.doFilter(request, response);
        return;
    }

    String path = request.getRequestURI();

    if (path.startsWith("/api/auth")) {
        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        if (jwtUtil.validateToken(token)) {
            var auth = new org.springframework.security.authentication
                    .UsernamePasswordAuthenticationToken(
                            jwtUtil.extractUsername(token),
                            null,
                            java.util.List.of()
                    );
            org.springframework.security.core.context.SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);
        }
    }

    filterChain.doFilter(request, response);
}

  
}
