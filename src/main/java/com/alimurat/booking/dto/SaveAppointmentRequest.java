package com.alimurat.booking.dto;

import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SaveAppointmentRequest {
    // Doctor ve Patient objeleri de gelebilir
    private LocalDateTime dateTime;
    private Integer duration;
    private String appointmentDesc;
    private Integer doctorId;
    private Integer patientId;
}
