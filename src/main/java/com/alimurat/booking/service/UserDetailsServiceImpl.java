package com.alimurat.booking.service;

import com.alimurat.booking.model.Patient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatientService patientService;

    public UserDetailsServiceImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient patient = patientService.getPatientByEmail(email);
        var roles = Stream.of(patient.getRole())
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                patient.getEmail(),
                patient.getPassword(),
                roles);
    }
}
