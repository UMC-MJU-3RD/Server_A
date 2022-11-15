package com.example.demo.config;

import com.example.demo.config.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse processValidationError(MethodArgumentNotValidException exception){

        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return new BaseResponse("유효성 검사 에러",builder.toString(), HttpStatus.BAD_REQUEST.value());
    }
}
