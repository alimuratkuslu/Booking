package com.alimurat.booking.controller;

import com.alimurat.booking.dto.AppointmentResponse;
import com.alimurat.booking.dto.EditAppointmentRequest;
import com.alimurat.booking.dto.SaveAppointmentRequest;
import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointments(){
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody SaveAppointmentRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.saveAppointment(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> editAppointment(@PathVariable Integer id, @RequestBody EditAppointmentRequest request){
        return ResponseEntity.ok(appointmentService.editAppointment(id, request));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentByDoctorId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getByDoctorId(id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getByPatientId(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointmentById(id);
    }
}
