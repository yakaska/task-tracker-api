package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Pattern;

public class SignupRequest {

    private String email;

    private String password;

    @Pattern(regexp = "ADMIN|USER", message = "Role must be either ADMIN or USER")
    private String role;

    public SignupRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
