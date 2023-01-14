package com.alimurat.booking.dto;

import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class AppointmentResponse {

    private Integer id;
    private LocalDateTime dateTime;
    private Integer duration;
    private String appointmentDesc;
    private Doctor doctor;
    private Patient patient;
}
