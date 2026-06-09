package com.upskillingerp.spingbootauthentication.service.admin;

import com.upskillingerp.spingbootauthentication.dto.admin.admin_request.CreateUserRequest;
import com.upskillingerp.spingbootauthentication.dto.admin.admin_response.GetUserListResponse;
import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(CreateUserRequest request) {

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

        return "User registered successfully";
    }

    public List<User> getRegisteredUsers() {
        return userRepository.findAll();
    }
}
