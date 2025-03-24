package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @Pattern(regexp = "ADMIN|USER", message = "UserRole must be either ADMIN or USER")
    private String role;

    public SignupRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
