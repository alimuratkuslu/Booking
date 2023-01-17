package com.alimurat.booking.dto;

import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SaveAppointmentRequest {

    @JsonFormat(pattern = "yyyy-MM-DD HH:mm")
    private LocalDateTime dateTime;

    private Integer duration;
    private String appointmentDesc;
    private Integer doctorId;
    private Integer patientId;
}
