package com.alimurat.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class AddPatientDto {

    private Integer doctorId;
    private Integer patientId;
}
