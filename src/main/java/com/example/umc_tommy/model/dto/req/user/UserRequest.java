package com.example.umc_tommy.model.dto.req.user;

import com.example.umc_tommy.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(length = 11)
    private String phoneNumber;

    @NotNull
    private Role role;

}
