package com.alimurat.booking.controller;

import com.alimurat.booking.dto.DoctorOfPatientDto;
import com.alimurat.booking.dto.PatientResponse;
import com.alimurat.booking.dto.SavePatientRequest;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Integer id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody SavePatientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.savePatient(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> editPatient(@PathVariable Integer id, @RequestBody SavePatientRequest request){
        return ResponseEntity.ok(patientService.editPatient(id, request));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorOfPatientDto> showDoctor(@PathVariable Integer id){
        return ResponseEntity.ok(patientService.showDoctor(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivatePatient(@PathVariable Integer id){
        patientService.deactivatePatient(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activatePatient(@PathVariable Integer id){
        patientService.activatePatient(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatientById(id);
    }
}
