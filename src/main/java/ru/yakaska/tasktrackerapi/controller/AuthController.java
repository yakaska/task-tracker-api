package ru.yakaska.tasktrackerapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.yakaska.tasktrackerapi.model.Role;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.payload.request.LoginRequest;
import ru.yakaska.tasktrackerapi.payload.request.SignupRequest;
import ru.yakaska.tasktrackerapi.payload.response.JwtResponse;
import ru.yakaska.tasktrackerapi.payload.response.MessageResponse;
import ru.yakaska.tasktrackerapi.repository.UserRepository;
import ru.yakaska.tasktrackerapi.security.JwtUtils;
import ru.yakaska.tasktrackerapi.security.UserDetailsImpl;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        String role = signupRequest.getRole();

        user.setRole(Role.valueOf(role));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
