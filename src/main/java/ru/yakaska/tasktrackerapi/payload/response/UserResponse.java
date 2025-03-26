package ru.yakaska.tasktrackerapi.payload.response;

import java.util.List;

public record UserResponse(Long id, String email, List<String> roles) {
}
