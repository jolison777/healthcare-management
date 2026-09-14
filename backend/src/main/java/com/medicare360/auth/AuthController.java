package com.medicare360.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(UserRepository users, PasswordEncoder encoder, JwtService jwt) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return users.findByEmail(request.email())
                .filter(user -> encoder.matches(request.password(), user.getPassword()))
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(
                        new LoginResponse(jwt.generate(user), user.getId(), user.getName(), user.getRole().name(), user.getEmail())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password"));
    }

    public record LoginRequest(String email, String password) {}
    public record LoginResponse(String token, Long userId, String name, String role, String email) {}
}
