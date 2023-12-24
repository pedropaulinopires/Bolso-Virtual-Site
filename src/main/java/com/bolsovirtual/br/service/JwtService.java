package com.bolsovirtual.br.service;

import java.util.Date;
import java.util.UUID;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
public class JwtService {
    private static final int TIME_EXPIRES = 600000;
    public static final String TOKEN_PASSWORD = "41989389-700f-4349-9a1d-5eb5e98ffc8a";

    public String generatedToken(UUID id) {
        return JWT.create().withSubject(id.toString()).withExpiresAt(new Date(new Date().getTime() + TIME_EXPIRES)).sign(Algorithm.HMAC512(TOKEN_PASSWORD));
    }

    public String readToken(String token) {
        String tokenValue = JWT.require(Algorithm.HMAC512(TOKEN_PASSWORD)).build().verify(token).getSubject();
        return tokenValue;
    }
}