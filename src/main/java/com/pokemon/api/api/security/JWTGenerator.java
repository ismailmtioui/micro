package com.pokemon.api.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwt;

import java.util.Date;

import static com.pokemon.api.api.security.SecurityConstants.JWT_EXPIRATION_TIME;
import static com.pokemon.api.api.security.SecurityConstants.JWT_SECRET;

@Component
public class JWTGenerator {
    public String generateJWT(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + JWT_EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }
    public String getUsernameFromJWT(String token) {
     Claims claims = Jwts.parser()
             .setSigningKey(SecurityConstants.JWT_SECRET)
             .parseClaimsJws(token)
             .getBody();
     return claims.getSubject();
    }
    public boolean validateJWT(String token) {
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT");
        }

    }
}
