package com.alimurat.booking.dto;

import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.model.Doctor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public final class PatientResponse {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;

    private String password;
    private Boolean isActive;

    private Doctor doctor;
    private List<Appointment> appointments;
}
