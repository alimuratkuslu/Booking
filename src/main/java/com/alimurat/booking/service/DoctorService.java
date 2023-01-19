package com.alimurat.booking.service;

import com.alimurat.booking.dto.DoctorResponse;
import com.alimurat.booking.dto.PatientResponse;
import com.alimurat.booking.dto.SaveDoctorRequest;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.model.Role;
import com.alimurat.booking.repository.DoctorRepository;
import com.alimurat.booking.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Doctor> getDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    public DoctorResponse getDoctorById(Integer id){

        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);

        return DoctorResponse.builder()
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .email(doctor.getEmail())
                .field(doctor.getField())
                .password(doctor.getPassword())
                .patients(doctor.getPatients())
                .appointments(doctor.getAppointments())
                .build();
    }

    public DoctorResponse saveDoctor(SaveDoctorRequest request){
        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .field(request.getField())
                .password(request.getPassword())
                .role(Role.DOCTOR)
                .build();

        final Doctor fromDbDoctor = doctorRepository.save(doctor);

        return DoctorResponse.builder()
                .id(fromDbDoctor.getId())
                .name(fromDbDoctor.getName())
                .surname(fromDbDoctor.getSurname())
                .email(fromDbDoctor.getEmail())
                .field(fromDbDoctor.getField())
                .password(fromDbDoctor.getPassword())
                .patients(fromDbDoctor.getPatients())
                .appointments(fromDbDoctor.getAppointments())
                .build();
    }

    public DoctorResponse editDoctor(Integer id, SaveDoctorRequest request){
        Doctor currentDoctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        currentDoctor.setName(request.getName());
        currentDoctor.setSurname(request.getSurname());
        currentDoctor.setEmail(request.getEmail());
        currentDoctor.setField(request.getField());
        currentDoctor.setPassword(request.getPassword());

        doctorRepository.save(currentDoctor);

        return DoctorResponse.builder()
                .id(currentDoctor.getId())
                .name(currentDoctor.getName())
                .surname(currentDoctor.getSurname())
                .email(currentDoctor.getEmail())
                .field(currentDoctor.getField())
                .password(currentDoctor.getPassword())
                .patients(currentDoctor.getPatients())
                .appointments(currentDoctor.getAppointments())
                .build();
    }

    public void addPatientToDoctor(Integer doctorId, Integer patientId){

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(RuntimeException::new);
        Patient patient = patientRepository.findById(patientId).orElseThrow(RuntimeException::new);

        patient.setDoctor(doctor);
        patientRepository.save(patient);

        List<Patient> patients = doctor.getPatients();
        patients.add(patient);
        doctorRepository.save(doctor);
    }

    public List<PatientResponse> showPatientsOfDoctor(Integer id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);

        List<Patient> patients = doctor.getPatients();

        return patients.stream().map(model ->
                PatientResponse.builder()
                        .id(model.getId())
                        .name(model.getName())
                        .surname(model.getSurname())
                        .email(model.getEmail())
                        .birthDate(model.getBirthDate())
                        .password(model.getPassword())
                        .isActive(model.getIsActive())
                        .build()).collect(Collectors.toList());

    }

    public Doctor getDoctorByEmail(String email){

        return doctorRepository.findByEmail(email);
    }

    public void deleteDoctorById(Integer id){
        doctorRepository.deleteById(id);
    }


}
