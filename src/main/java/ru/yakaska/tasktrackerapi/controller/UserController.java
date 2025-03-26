package ru.yakaska.tasktrackerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yakaska.tasktrackerapi.mapper.UserMapper;
import ru.yakaska.tasktrackerapi.payload.response.UserResponse;
import ru.yakaska.tasktrackerapi.security.DefaultUserDetails;
import ru.yakaska.tasktrackerapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal DefaultUserDetails userDetails) {
        UserResponse userResponse = new UserResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Long id) {
        UserResponse userResponse = userMapper.toUserResponse(userService.findById(id));

        return ResponseEntity.ok(userResponse);
    }


}
