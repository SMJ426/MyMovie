package com.example.movies.domain.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
