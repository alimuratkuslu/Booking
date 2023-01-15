package com.alimurat.booking.service;

import com.alimurat.booking.dto.LoginForm;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.repository.DoctorRepository;
import com.alimurat.booking.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AuthService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public boolean login(LoginForm loginForm){
        Patient patient = patientRepository.findByEmail(loginForm.getEmail());
        Doctor doctor = doctorRepository.findByEmail(loginForm.getEmail());

        if((doctor != null && doctor.getPassword().equals(loginForm.getPassword())
                || (patient != null && patient.getPassword().equals(loginForm.getPassword())))){
            return true;
        }
        return false;
    }
}
