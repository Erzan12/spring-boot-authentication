package com.upskillingerp.spingbootauthentication.service;

import com.upskillingerp.spingbootauthentication.dto.LoginRequest;
import com.upskillingerp.spingbootauthentication.dto.RegisterRequest;
import com.upskillingerp.spingbootauthentication.dto.auth.AuthResponse;
import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if(userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {
            throw new RuntimeException(
                    "Email already exist"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .age(request.getAge())
                .address(request.getAddress())
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User registered";
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(!matches) {
            throw new RuntimeException(
                    "Invalid credentials"
            );
        }

        String token =
                jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
