package ru.yakaska.tasktrackerapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.repository.UserRepository;
import ru.yakaska.tasktrackerapi.security.DefaultUserDetails;

import java.util.Optional;

@Service
public class DefaultUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return DefaultUserDetails.build(user);
    }
}
