package com.upskillingerp.spingbootauthentication.service;

import com.upskillingerp.spingbootauthentication.entity.User;
import com.upskillingerp.spingbootauthentication.helper.JwtHelper;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // with helper configuration
//    private final JwtHelper jwtHelper;

    // with bean configuration
    private final Key jwtSigningKey;

    public JwtService(Key jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    private static final Logger log =
            LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

//    @PostConstruct
//    public void init() {
//        System.out.println("JWT Secret: " + secret);
//        System.out.println("JWT Expiration: " + expiration);
//    }

    @PostConstruct
    public void init() {
        log.info("JWT expiration: {}", expiration);
        log.info("JWT secret: {}", secret);
    }

    public void printConfig() {
        System.out.println(secret);
        System.out.println(expiration);
    }

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + 3600000
                        )
                )
                // with helper configuration
//                .signWith(jwtHelper.getSignInKey())

                // with bean configuration
                .signWith(jwtSigningKey)
                .compact();
    }
}
