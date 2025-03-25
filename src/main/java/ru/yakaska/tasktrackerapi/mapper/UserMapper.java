package ru.yakaska.tasktrackerapi.mapper;

import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.repository.UserRepository;

@Component
public class UserMapper {

    private final UserRepository userRepository;

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Named("emailToUser")
    public User emailToUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }
}