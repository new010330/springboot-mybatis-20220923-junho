package com.boot.mybatis20220923junho.dto;

import com.boot.mybatis20220923junho.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class SignupReqDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;

    public User toEntity() {
        return User.builder()
                .user_id(userId)
                .user_password(userPassword)
                .user_name(userName)
                .user_email(userEmail)
                .build();
    }
}
