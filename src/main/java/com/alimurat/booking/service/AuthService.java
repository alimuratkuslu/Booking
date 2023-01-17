package com.alimurat.booking.service;

import com.alimurat.booking.dto.LoginForm;
import com.alimurat.booking.dto.TokenResponseDto;
import com.alimurat.booking.repository.DoctorRepository;
import com.alimurat.booking.repository.PatientRepository;
import com.alimurat.booking.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final PatientService patientService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(PatientRepository patientRepository, DoctorRepository doctorRepository, PatientService patientService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.patientService = patientService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginForm loginForm){

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                .response(patientService.getPatientByEmail(loginForm.getEmail()))
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
}
