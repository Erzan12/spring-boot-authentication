package com.upskillingerp.spingbootauthentication.service.admin;

import com.upskillingerp.spingbootauthentication.dto.admin.admin_request.CreateUserRequest;
import com.upskillingerp.spingbootauthentication.dto.admin.admin_request.UpdateUserRequest;
import com.upskillingerp.spingbootauthentication.dto.admin.admin_response.GetUserListResponse;
import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.exception.EmailAlreadyExistsException;
import com.upskillingerp.spingbootauthentication.exception.NotFoundException;
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
            throw new EmailAlreadyExistsException("Email already exists");
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

    public String updateRegisteredUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }

        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        userRepository.save(user);

        return "User updated successfully";
    }

    public List<GetUserListResponse> getRegisteredUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    GetUserListResponse response = new GetUserListResponse();
                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setEmail(user.getEmail());
                    response.setAge(user.getAge());
                    response.setAddress(user.getAddress());
                    response.setRole(user.getRole());

                    return response;
                })
                .toList();
    }
}
