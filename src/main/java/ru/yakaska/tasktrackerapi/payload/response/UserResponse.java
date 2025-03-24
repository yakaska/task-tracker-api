package ru.yakaska.tasktrackerapi.payload.response;

import ru.yakaska.tasktrackerapi.model.User;

import java.util.Collections;
import java.util.List;

public record UserResponse(Long id, String email, List<String> roles) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                Collections.singletonList(user.getRole().name())
        );
    }
}
