package com.example.umc_tommy.controller.api.user;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.board.BoardRequest;
import com.example.umc_tommy.model.dto.req.user.UserRequest;
import com.example.umc_tommy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<DefaultRes> Board(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
    }
}
