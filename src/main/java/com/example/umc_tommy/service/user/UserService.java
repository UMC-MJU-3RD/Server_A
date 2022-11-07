package com.example.umc_tommy.service.user;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.user.UserRequest;
import com.example.umc_tommy.model.entity.user.User;
import com.example.umc_tommy.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public DefaultRes createUser(UserRequest request) {

        User user = User.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(user);
        
        if(savedUser != null){
            return DefaultRes.response(HttpStatus.OK.value(), "사용자 저장 성공");
        }
        else{
            return DefaultRes.response(HttpStatus.OK.value(), "사용자 저장 실패");
        }
    }
}
