package com.openclassrooms.mddapi.services.interfaces;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

public interface IJWTService {

    String generateToken(final String email);
    Jwt claimsEmail(final String token);
}
