package com.upskillingerp.spingbootauthentication.config;

import com.upskillingerp.spingbootauthentication.service.JwtService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {

    private static final Logger log =
            LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String secret;

    // with bean configuration
    @Bean
    public Key jwtSigningKey() {

        log.info("JWT Secret = " + secret);

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
