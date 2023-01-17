package com.alimurat.booking.controller;

import com.alimurat.booking.dto.LoginForm;
import com.alimurat.booking.dto.TokenResponseDto;
import com.alimurat.booking.repository.PatientRepository;
import com.alimurat.booking.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginForm loginForm){

        return ResponseEntity.ok(authService.login(loginForm));

        /*
        boolean result = authService.login(loginForm);

        if(result){
            return ResponseEntity.ok().body("Successful Login");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
         */
    }

    @GetMapping("/myself")
    public String myself(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/method-admin")
    public String method_admin(){
        return "Only admins can see";
    }
}
