package com.example.api.security;

import com.example.api.model.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.api.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.api.security.SecurityConstants.SECRET_KEY;

@Component
public class JWTProvider {

    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setExpiration(expiryDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            System.out.println("Invalid Jwt signature");
        }catch (MalformedJwtException e){
            System.out.println("Invalid Jwt token");
        }catch (ExpiredJwtException e){
            System.out.println("Expired Jwt token");
        }catch (UnsupportedJwtException e){
            System.out.println("Unsupported Jwt token");
        }catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
