package com.alimurat.booking.service;

import com.alimurat.booking.dto.DoctorOfPatientDto;
import com.alimurat.booking.dto.PatientResponse;
import com.alimurat.booking.dto.SavePatientRequest;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.model.Role;
import com.alimurat.booking.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    //private final static Logger logger = (Logger) LoggerFactory.getLogger(PatientService.class);


    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patient> getPatients(){
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    public PatientResponse getPatientById(Integer id){
        if(doesPatientExist(id)){
            Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);

            return PatientResponse.builder()
                    .id(patient.getId())
                    .name(patient.getName())
                    .surname(patient.getSurname())
                    .email(patient.getEmail())
                    .birthDate(patient.getBirthDate())
                    .password(patient.getPassword())
                    .isActive(patient.getIsActive())
                    .doctor(patient.getDoctor())
                    .appointments(patient.getAppointments())
                    .build();
        }
        else{
            throw new RuntimeException("Patient does not exist");
        }
    }

    public PatientResponse savePatient(SavePatientRequest request){
        Patient patient = Patient.builder()
                .name(request.getName())
                .surname(request.getSurname())
                        .email(request.getEmail())
                        .birthDate(request.getBirthDate())
                        .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                        .isActive(true)
                        .build();
        //passwordEncoder.encode(request.getPassword())
        final Patient fromDbPatient = patientRepository.save(patient);

        return PatientResponse.builder()
                .id(fromDbPatient.getId())
                .name(fromDbPatient.getName())
                .surname(fromDbPatient.getSurname())
                .email(fromDbPatient.getEmail())
                .birthDate(fromDbPatient.getBirthDate())
                .password(fromDbPatient.getPassword())
                .isActive(true)
                .doctor(fromDbPatient.getDoctor())
                .appointments(fromDbPatient.getAppointments())
                .build();
    }

    public PatientResponse editPatient(Integer id, SavePatientRequest request){
        Patient currentPatient = patientRepository.findById(id).orElseThrow(RuntimeException::new);

        if(!currentPatient.getIsActive()){
            //logger.warning(String.format("The patient  is not active with id: %s", id));
            throw new RuntimeException("The patient is not active");
        }

        currentPatient.setName(request.getName());
        currentPatient.setSurname(request.getSurname());
        currentPatient.setEmail(request.getEmail());
        currentPatient.setBirthDate(request.getBirthDate());
        currentPatient.setPassword(request.getPassword());

        patientRepository.save(currentPatient);

        return PatientResponse.builder()
                .id(currentPatient.getId())
                .name(currentPatient.getName())
                .surname(currentPatient.getSurname())
                .email(currentPatient.getEmail())
                .birthDate(currentPatient.getBirthDate())
                .password(currentPatient.getPassword())
                .isActive(true)
                .doctor(currentPatient.getDoctor())
                .appointments(currentPatient.getAppointments())
                .build();
    }

    public DoctorOfPatientDto showDoctor(Integer id){
        Patient currentPatient = patientRepository.findById(id).orElseThrow(RuntimeException::new);

        return DoctorOfPatientDto.builder()
                .name(currentPatient.getName())
                .surname(currentPatient.getSurname())
                .email(currentPatient.getEmail())
                .birthDate(currentPatient.getBirthDate())
                .doctorName(currentPatient.getDoctor().getName())
                .doctorSurname(currentPatient.getDoctor().getSurname())
                .doctorEmail(currentPatient.getDoctor().getEmail())
                .doctorField(currentPatient.getDoctor().getField())
                .build();
    }
    public void deactivatePatient(Integer id){
        changePatientStatus(id, false);
    }

    public void activatePatient(Integer id){
        changePatientStatus(id, true);
    }

    public void deletePatientById(Integer id){
        if(doesPatientExist(id)){
            patientRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Patient does not exist");
        }
    }

    private void changePatientStatus(Integer id, Boolean isActive){
        Patient currentPatient = patientRepository.findById(id).orElseThrow(RuntimeException::new);

        currentPatient.setIsActive(isActive);
        patientRepository.save(currentPatient);
    }

    public Patient getPatientByEmail(String email){

        return patientRepository.findByEmail(email);
    }

    private boolean doesPatientExist(Integer id){
        return patientRepository.existsById(id);
    }

}
