package com.example.umc_tommy.utils;

import com.example.umc_tommy.model.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static Key key;

    // Refresh Token  유효시간 2주 (ms 단위)
    public static Long REFRESH_TOKEN_VALID_TIME = 14 * 1440 * 60 * 1000L;

    //엑세스 토큰 유효시간 15분
    public static Long ACCESS_TOKEN_VALID_TIME =  15 * 60 * 1000L;

    public static String ACCESS_TOKEN_NAME = "ACCESS TOKEN";

    public static String REFRESH_TOKEN_NAME = "REFRESH TOKEN";


    public JwtUtil(String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //엑세스 토큰 발급
    public static String createAccessToken(Long userId, Role userRole){

        Date now = new Date();

        return Jwts.builder()
                .claim("user_pk", userId) // 정보 저장
                .claim("user_role", userRole)
                .claim("token_name", ACCESS_TOKEN_NAME)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    //리프레쉬 토큰 발급
    public static String createRefreshToken(Long userId, Role userRole){

        Date now = new Date();

        return Jwts.builder()
                .claim("user_pk", userId) //정보 저장
                .claim("user_role", userRole)
                .claim("token_name", REFRESH_TOKEN_NAME)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰의 유효성 + 만료일자 확인
    public static Claims getClaimsFromToken(String token) {
        try {
                return Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build().parseClaimsJws(token)
                        .getBody();

        }catch (Exception e){
            return null;
        }
    }

    public boolean notExpiredToken(String token) {
        return !expiredToken(token);
    }

    public boolean expiredToken(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }
}
