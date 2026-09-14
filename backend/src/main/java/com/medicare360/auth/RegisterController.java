package com.medicare360.auth;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @CrossOrigin(origins="http://localhost:4200") public class RegisterController{
 private final UserRepository users; private final PasswordEncoder encoder; public RegisterController(UserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
 @PostMapping("/register") public User register(@RequestBody RegisterRequest r){if(users.findByEmail(r.email()).isPresent())throw new IllegalArgumentException("Email already registered");return users.save(new User(r.name(),r.email(),encoder.encode(r.password()),Role.PATIENT));}
 public record RegisterRequest(String name,String email,String password){}
}
