package com.medicare360.auth;
import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @CrossOrigin(origins="http://localhost:4200")
public class AuthController{
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest r){return users.findByEmail(r.email()).filter(u->encoder.matches(r.password(),u.getPassword())).map(u->ResponseEntity.ok(new LoginResponse(jwt.generate(u),u.getId(),u.getName(),u.getRole().name(),u.getEmail()))).orElseGet(()->ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password"));}
 public record LoginRequest(String email,String password){}
 public record LoginResponse(String token,Long userId,String name,String role,String email){}
}
