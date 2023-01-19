package com.alimurat.booking.dto;

import com.alimurat.booking.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorTokenResponse {

    private String accessToken;
    private Doctor response;
}
