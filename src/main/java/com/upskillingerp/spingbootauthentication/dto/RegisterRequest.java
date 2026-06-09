package com.upskillingerp.spingbootauthentication.dto;

import com.upskillingerp.spingbootauthentication.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Integer age;
    private String address;
}
