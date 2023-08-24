package com.example.movies.service;

import com.example.movies.domain.User.Principal;
import com.example.movies.domain.User.Role;
import com.example.movies.domain.User.User;
import com.example.movies.dto.UserDto;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    void signup() {
        UserDto userDto = UserDto.builder()
                .userName("정지현")
                .email("jj1234@naver.com")
                .password("12345678")
                .role(Role.Member).build();
        userService.signup(userDto);
        User user = User.builder()
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
        Principal principal = new Principal(user);
        assertEquals("정지현",principal.getUsername());

    }
    @Test
    @Valid
    void valid(){
        User user = User.builder()
                .birth("990531")
                .build();
        System.out.println(user.getBirth());
    }
}