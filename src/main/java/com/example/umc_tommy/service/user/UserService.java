package com.example.umc_tommy.service.user;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.user.UserRequest;
import com.example.umc_tommy.model.entity.user.User;
import com.example.umc_tommy.model.enums.Role;
import com.example.umc_tommy.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public DefaultRes createUser(UserRequest request) {
        Role role;

        if(request.getRole().equals(Role.GUEST.toString())){
            role = Role.GUEST;
        }
        else{
            role = Role.USER;
        }

        User user = User.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .role(role)
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

    public DefaultRes<List<User>> getUsers() {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "사용자가 없습니다.");
        }
        else{
            return DefaultRes.response(HttpStatus.OK.value(), "사용자 조회 성공.", userList);
        }
    }
}
