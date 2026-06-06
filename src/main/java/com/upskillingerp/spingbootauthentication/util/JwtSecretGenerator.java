package com.upskillingerp.spingbootauthentication.util;

import com.upskillingerp.spingbootauthentication.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtSecretGenerator {

    private static final Logger log =
            LoggerFactory.getLogger(JwtService.class);

    public static void main(String[] args) {

        String secret =
                Encoders.BASE64.encode(
                        Jwts.SIG.HS256.key()
                                .build()
                                .getEncoded()
                );

        log.info("JWT SECRET: {}", secret);
    }
}
