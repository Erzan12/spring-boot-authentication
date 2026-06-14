package com.upskillingerp.spingbootauthentication.dto.user.user_response;

import com.upskillingerp.spingbootauthentication.enums.Role;
import lombok.Data;

@Data
public class GetUserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String address;
    private Role role;
}
