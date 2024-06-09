package com.binhlc.movieticketadmin.util;

import com.binhlc.movieticketadmin.common.CodeDefineJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.binhlc.movieticketadmin.common.CodeDefineJWT.SECRET;

public class JWTutils {
    protected static Logger logger = LoggerFactory.getLogger(JWTutils.class);

    public static String generate(Map<String, String> map) {
        try {
            String userId = map.get("user_id");
            String roles = map.get("roles");
            Claims claims = Jwts.claims().setSubject(userId);
            claims.put("roles", roles);
            Date date = new Date();

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(date)
                    .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SECRET.getBytes()))
                    .setExpiration(new Date(date.getTime() + CodeDefineJWT.TIME_TOKEN_EXPIRE))
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JWTUtils generate error", e);
            return null;
        }
    }

    public static Map<String, String> validate(String token){
        Map<String, String> map = new HashMap<>();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(CodeDefineJWT.SECRET.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
            map.put("user_id", claims.getSubject());
            map.put("roles", claims.get("roles").toString());
        } catch (ExpiredJwtException e) {
            // Handle expired token
            System.out.println("Token has expired");
            map.put("error", "Token has expired");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Invalid token");
        }
        return map;
    }





}
