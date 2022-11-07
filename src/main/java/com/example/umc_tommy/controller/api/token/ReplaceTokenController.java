package com.example.umc_tommy.controller.api.token;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.service.user.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplaceTokenController {

    private final SessionService sessionService;

    @GetMapping("/token")
    public ResponseEntity<DefaultRes> replaceToken(
            HttpServletRequest request
    ){
        DefaultRes defaultRes = sessionService.replaceToken(request);

        if (defaultRes.getStatusCode() == HttpStatus.UNAUTHORIZED.value()){
            return new ResponseEntity<>(defaultRes, HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }

    }
}
