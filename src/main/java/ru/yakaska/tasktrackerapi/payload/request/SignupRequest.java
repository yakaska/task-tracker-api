package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    private final String email;

    private final String password;

    @Pattern(regexp = "ADMIN|USER", message = "Role must be either ADMIN or USER")
    private String role;

    public SignupRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
