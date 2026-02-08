package com.pank.pank.middlewares;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.pank.pank.models.UserEntity;
import com.pank.pank.repositories.UserRepository;
import com.pank.pank.utilities.FirebaseTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FirebaseAuthFilter extends OncePerRequestFilter {

    @Autowired
    private FirebaseTokenUtil firebaseTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String idToken = authHeader.substring(7);

        try {
            FirebaseToken token = firebaseTokenUtil.verify(idToken);

            String firebaseUid = token.getUid();
            String did = firebaseTokenUtil.getDid(token);
            String role = firebaseTokenUtil.getRole(token);

            UserEntity user = userRepository
                    .findByFirebaseUid(firebaseUid)
                    .orElseThrow();

            // Optional consistency checks
            if (!user.getPublicId().equals(did)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (!user.getRole().name().equals(role)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            request.setAttribute("user", user);
            filterChain.doFilter(request, response);

        } catch (FirebaseAuthException | RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/public/**").permitAll()
                            .anyRequest().authenticated())
                    .addFilterBefore(
                            firebaseAuthFilter(),
                            UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public FirebaseAuthFilter firebaseAuthFilter() {
            return new FirebaseAuthFilter();
        }
    }

}
