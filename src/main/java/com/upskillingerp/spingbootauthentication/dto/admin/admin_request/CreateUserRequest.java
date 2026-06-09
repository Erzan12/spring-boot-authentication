package com.upskillingerp.spingbootauthentication.dto.admin.admin_request;

import com.upskillingerp.spingbootauthentication.enums.Role;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private Integer age;
    private String address;
    private Role role;
}
