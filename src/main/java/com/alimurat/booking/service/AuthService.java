package com.alimurat.booking.service;

import com.alimurat.booking.dto.DoctorTokenResponse;
import com.alimurat.booking.dto.LoginForm;
import com.alimurat.booking.dto.TokenResponseDto;
import com.alimurat.booking.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PatientService patientService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    private final DoctorService doctorService;

    public AuthService(PatientService patientService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager, DoctorService doctorService) {
        this.patientService = patientService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
        this.doctorService = doctorService;
    }

    // Gets the logged in user's email
    public String getLoggedInEmail(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public TokenResponseDto login(LoginForm loginForm){

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                //.response(patientService.getPatientByEmail(loginForm.getEmail()))
                .build();

        /*
        Patient patient = patientRepository.findByEmail(loginForm.getEmail());
        Doctor doctor = doctorRepository.findByEmail(loginForm.getEmail());

        if((doctor != null && doctor.getPassword().equals(loginForm.getPassword())
                || (patient != null && patient.getPassword().equals(loginForm.getPassword())))){
            return true;
        }
        return false;
         */
    }

    public TokenResponseDto doctorLogin(LoginForm loginForm){

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                //.response(doctorService.getDoctorByEmail(loginForm.getEmail()))
                .build();
    }
}
