package com.upskillingerp.spingbootauthentication.service.user;

import com.upskillingerp.spingbootauthentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public
}
