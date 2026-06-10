package com.upskillingerp.spingbootauthentication.controller;

import com.upskillingerp.spingbootauthentication.dto.LoginRequest;
import com.upskillingerp.spingbootauthentication.dto.RegisterRequest;
import com.upskillingerp.spingbootauthentication.dto.api_response.ApiResponse;
import com.upskillingerp.spingbootauthentication.dto.auth.AuthResponse;
import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.repository.UserRepository;
import com.upskillingerp.spingbootauthentication.service.AuthService;
import com.upskillingerp.spingbootauthentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        AuthResponse response =
                authService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }
}
