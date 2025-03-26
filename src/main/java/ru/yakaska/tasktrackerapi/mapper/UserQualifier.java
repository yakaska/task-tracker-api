package ru.yakaska.tasktrackerapi.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.model.UserRole;
import ru.yakaska.tasktrackerapi.service.UserService;

import java.util.Collections;
import java.util.List;

@Component
public class UserQualifier {

    private final UserService userService;

    public UserQualifier(UserService userService) {
        this.userService = userService;
    }

    @Named("emailToUser")
    public User emailToUser(String email) {
        return userService.findByEmail(email);
    }

    @Named("mapRoles")
    public List<String> mapRoles(UserRole role) {
        return Collections.singletonList(role.name());
    }
}
