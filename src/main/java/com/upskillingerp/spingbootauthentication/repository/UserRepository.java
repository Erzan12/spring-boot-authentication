package com.upskillingerp.spingbootauthentication.repository;

import com.upskillingerp.spingbootauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
