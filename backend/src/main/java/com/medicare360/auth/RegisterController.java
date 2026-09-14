package com.medicare360.auth;
import com.medicare360.patient.Patient; import com.medicare360.patient.PatientRepository; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @CrossOrigin(origins="http://localhost:4200") public class RegisterController{
 private final UserRepository users; private final PatientRepository patients; private final PasswordEncoder encoder;
 public RegisterController(UserRepository users,PatientRepository patients,PasswordEncoder encoder){this.users=users;this.patients=patients;this.encoder=encoder;}
 @PostMapping("/register") public User register(@RequestBody RegisterRequest r){if(users.findByEmail(r.email()).isPresent())throw new IllegalArgumentException("Email already registered");User u=users.save(new User(r.name(),r.email(),encoder.encode(r.password()),Role.PATIENT));Patient p=new Patient();p.setName(r.name());p.setEmail(r.email());patients.save(p);return u;}
 public record RegisterRequest(String name,String email,String password){}
}
