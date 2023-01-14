package com.alimurat.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public final class DoctorOfPatientDto {

    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;

    private String doctorName;
    private String doctorSurname;
    private String doctorEmail;
    private String doctorField;
}
