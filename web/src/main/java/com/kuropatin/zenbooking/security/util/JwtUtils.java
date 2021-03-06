package com.kuropatin.zenbooking.security.util;

import com.kuropatin.zenbooking.config.JwtConfig;
import com.kuropatin.zenbooking.util.ApplicationTimeUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    public static final String CREATED = "created";
    public static final String ROLES = "roles";
    private final JwtConfig jwtConfig;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(CREATED, ApplicationTimeUtils.getCurrentDate());
        claims.put(ROLES, getEncryptedRoles(userDetails));
        return generateTokenFromClaims(claims);
    }

    private List<String> getEncryptedRoles(UserDetails userDetails) {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(s -> s.replace("ROLE_", ""))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private String generateTokenFromClaims(Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    private Date generateExpirationDate() {
        return ApplicationTimeUtils.getExpirationDate(jwtConfig.getExpiration()); //expiration time in minutes
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername());
    }

    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}