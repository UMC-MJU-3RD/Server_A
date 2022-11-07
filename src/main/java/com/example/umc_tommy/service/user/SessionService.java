package com.example.umc_tommy.service.user;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.ReplaceTokenResponseDto;
import com.example.umc_tommy.model.entity.User;
import com.example.umc_tommy.model.enums.Role;
import com.example.umc_tommy.repository.user.UserRepository;
import com.example.umc_tommy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;

    public DefaultRes replaceToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        if (token == null) {
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "요청 에러");
        }

        String jwtToken = token.substring("Bearer ".length());
        Claims claims;
        try{
            claims = JwtUtil.getClaimsFromToken(jwtToken);
        }catch (Exception e){
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치");
        }

        String tokenName = claims.get("token_name", String.class);

        if(tokenName.equals(JwtUtil.REFRESH_TOKEN_NAME)){

            Long userPk = claims.get("user_pk", Long.class);
            Role userRole = claims.get("user_role", Role.class);

            Boolean check = refreshTokenCheck(userPk, jwtToken);
            if(check){
                String accessToken;
                accessToken = JwtUtil.createAccessToken(userPk, userRole);
                return DefaultRes.response(HttpStatus.OK.value(), "토큰 재발급", new ReplaceTokenResponseDto(accessToken));
            }else{
                return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치");
            }

        }else{
            return DefaultRes.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치");
        }
    }

    public Boolean refreshTokenCheck(Long id, String refreshToken){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> user.getToken().equals(refreshToken)).orElse(false);
    }
}
