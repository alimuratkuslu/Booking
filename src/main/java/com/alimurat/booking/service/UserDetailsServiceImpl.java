package com.alimurat.booking.service;

import com.alimurat.booking.model.Doctor;
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

    private final DoctorService doctorService;


    public UserDetailsServiceImpl(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(patientService.getPatientByEmail(email) != null){
            Patient patient = patientService.getPatientByEmail(email);
            var roles = Stream.of(patient.getRole())
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    patient.getEmail(),
                    patient.getPassword(),
                    roles);
        }
        else{
            Doctor doctor = doctorService.getDoctorByEmail(email);
            var roles = Stream.of(doctor.getRole())
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    doctor.getEmail(),
                    doctor.getPassword(),
                    roles);
        }
    }
}
