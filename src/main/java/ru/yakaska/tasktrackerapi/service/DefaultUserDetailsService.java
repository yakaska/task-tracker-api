package ru.yakaska.tasktrackerapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.security.DefaultUserDetails;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public DefaultUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        return DefaultUserDetails.build(user);
    }
}
