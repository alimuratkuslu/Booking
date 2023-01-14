package com.alimurat.booking.dto;

import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.model.Patient;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class DoctorResponse {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String field;
    private String password;
    private List<Patient> patients;
    private List<Appointment> appointments;
}
