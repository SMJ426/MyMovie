package com.example.movies.dto;

import com.example.movies.domain.User.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class UserDto {
    private String email;
    private String userName;
    private String password;
    private String birth;
    private String phoneNum;
    private String address;
    private Role role;

}
