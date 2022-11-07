package com.example.umc_tommy.controller.api.user;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.board.BoardRequest;
import com.example.umc_tommy.model.dto.req.user.UserRequest;
import com.example.umc_tommy.model.entity.user.User;
import com.example.umc_tommy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<DefaultRes> createUser(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<DefaultRes<List<User>>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
