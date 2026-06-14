package com.upskillingerp.spingbootauthentication.controller;

import com.upskillingerp.spingbootauthentication.dto.admin.admin_request.CreateUserRequest;
import com.upskillingerp.spingbootauthentication.dto.admin.admin_request.UpdateUserRequest;
import com.upskillingerp.spingbootauthentication.dto.admin.admin_response.GetUserListResponse;
import com.upskillingerp.spingbootauthentication.dto.api_response.ApiResponse;
import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.service.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerUser(
            @RequestBody CreateUserRequest request
    ) {
        return adminService.registerUser(request);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRegisteredUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return adminService.updateRegisteredUser(id, request);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<GetUserListResponse>>> getRegisteredUsers() {

        List<GetUserListResponse> responses = adminService.getRegisteredUsers();

        ApiResponse<List<GetUserListResponse>> response =
                new ApiResponse<>(
                        true,
                        "Users fetch successfully",
                        responses
                );

        return ResponseEntity.ok(response);
    }
}
