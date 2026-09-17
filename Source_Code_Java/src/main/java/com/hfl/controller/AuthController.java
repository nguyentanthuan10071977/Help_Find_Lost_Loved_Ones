package com.hfl.controller;
import com.hfl.model.*; import com.hfl.repository.AppUserRepository; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AppUserRepository repo; private final PasswordEncoder encoder;
 public AuthController(AppUserRepository repo,PasswordEncoder encoder){this.repo=repo;this.encoder=encoder;}
 @PostMapping("/register") public ResponseEntity<?> register(@RequestBody AppUser u){
  if(repo.findByEmail(u.getEmail()).isPresent()) return ResponseEntity.status(409).body("Email already exists");
  u.setId(null);u.setPassword(encoder.encode(u.getPassword()));u.setRole(Role.USER);return ResponseEntity.status(201).body(repo.save(u));
 }
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody AppUser u){
  return repo.findByEmail(u.getEmail()).filter(x->encoder.matches(u.getPassword(),x.getPassword()))
    .<ResponseEntity<?>>map(x->ResponseEntity.ok(java.util.Map.of("userId",x.getId(),"name",x.getName(),"role",x.getRole())))
    .orElse(ResponseEntity.status(401).body("Invalid email or password"));
 }
}
