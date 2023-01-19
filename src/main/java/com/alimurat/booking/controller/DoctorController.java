package com.alimurat.booking.controller;

import com.alimurat.booking.dto.AddPatientDto;
import com.alimurat.booking.dto.DoctorResponse;
import com.alimurat.booking.dto.PatientResponse;
import com.alimurat.booking.dto.SaveDoctorRequest;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors(){
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Integer id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody SaveDoctorRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(doctorService.saveDoctor(request));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<List<PatientResponse>> showPatientsOfDoctor(@PathVariable Integer id){
        return ResponseEntity.ok(doctorService.showPatientsOfDoctor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> editDoctor(@PathVariable Integer id, @RequestBody SaveDoctorRequest request){
        return ResponseEntity.ok(doctorService.editDoctor(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctorById(id);
    }

    @PostMapping("/addPatient")
    public void addPatientToDoctor(@RequestBody AddPatientDto addPatientDto){
        doctorService.addPatientToDoctor(addPatientDto.getDoctorId(), addPatientDto.getPatientId());
    }
    @GetMapping("/details/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email){
        return ResponseEntity.ok(doctorService.getDoctorByEmail(email));
    }

}
