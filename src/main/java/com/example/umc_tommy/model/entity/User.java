package com.example.umc_tommy.model.entity;

import com.example.umc_tommy.model.enums.Grade;
import com.example.umc_tommy.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성을 데이터베이스에 위임
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String nickName;

    private String password;

    private String phoneNumber;

    private int point = 0;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.GRATEFUL;

    private String token; // 리프레시 토큰

    private String profileUrl;

    @Builder
    public User(String email, String nickName, Role role, String phoneNumber) {
        this.email = email;
        this.nickName = nickName;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", point='" + point + '\'' +
                ", role='" + role + '\'' +
                ", grade='" + grade + '\'' +
                super.toString() +
                '}';
    }

    public void setRefreshToken(String refreshToken) {
        this.token = refreshToken;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
