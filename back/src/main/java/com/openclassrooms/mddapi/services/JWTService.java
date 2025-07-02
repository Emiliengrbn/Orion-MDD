package com.openclassrooms.mddapi.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.openclassrooms.mddapi.services.interfaces.IJWTService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JWTService implements IJWTService {


    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Jwt claimsEmail(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt;
    }

    @Override
    public String generateToken(final String email) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(email)
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }



}
