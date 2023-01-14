package com.alimurat.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class SaveDoctorRequest {

    private String name;
    private String surname;
    private String email;
    private String field;
    private String password;
}
