package ru.yakaska.tasktrackerapi.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtResponse {

    private String jwt;

    private Long id;

    private String email;

    private List<String> roles;

    public JwtResponse(String jwt, Long id, String email, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

}
