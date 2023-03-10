package com.alimurat.booking.dto;

import com.alimurat.booking.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SavePatientRequest {

    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;

    private String password;
}
