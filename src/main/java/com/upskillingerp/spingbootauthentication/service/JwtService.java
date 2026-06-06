package com.upskillingerp.spingbootauthentication.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

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
}
