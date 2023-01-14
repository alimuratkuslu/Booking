package com.alimurat.booking.controller;

import com.alimurat.booking.dto.*;
import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.service.AppointmentService;
import com.alimurat.booking.service.DoctorService;
import com.alimurat.booking.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "integration")
class AppointmentControllerTest {

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PatientService patientService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldSaveAppointment() throws Exception{

        SaveDoctorRequest doctorRequest = SaveDoctorRequest.builder()
                .name("Doğan")
                .surname("Kuşlu")
                .email("dogan@gmail.com")
                .field("Diyetisyen")
                .password("doganpassword")
                .build();

        DoctorResponse doctorResponse = DoctorResponse.builder()
                .id(1)
                .name("Doğan")
                .surname("Kuşlu")
                .email("dogan@gmail.com")
                .field("Diyetisyen")
                .password("doganpassword")
                .patients(null)
                .appointments(null)
                .build();

        SavePatientRequest patientRequest = SavePatientRequest.builder()
                .name("Ali Murat")
                .surname("Kuşlu")
                .email("ali@gmail.com")
                .birthDate(LocalDate.now())
                .password("alipassword")
                .build();

        PatientResponse patientResponse = PatientResponse.builder()
                .id(1)
                .name("Ali Murat")
                .surname("Kuşlu")
                .email("ali@gmail.com")
                .birthDate(LocalDate.now())
                .password("alipassword")
                .build();

        doctorService.saveDoctor(doctorRequest);
        patientService.savePatient(patientRequest);

        Doctor doctor = Doctor.builder()
                .id(1)
                .name(doctorResponse.getName())
                .surname(doctorResponse.getSurname())
                .email(doctorResponse.getEmail())
                .field(doctorResponse.getField())
                .password(doctorResponse.getPassword())
                .patients(null)
                .appointments(null)
                .build();

        Patient patient = Patient.builder()
                .id(1)
                .name(patientResponse.getName())
                .surname(patientResponse.getSurname())
                .email(patientResponse.getEmail())
                .birthDate(patientResponse.getBirthDate())
                .password(patientResponse.getPassword())
                .isActive(patientResponse.getIsActive())
                .doctor(patientResponse.getDoctor())
                .appointments(patientResponse.getAppointments())
                .build();

        SaveAppointmentRequest request = SaveAppointmentRequest.builder()
                .dateTime(LocalDateTime.now())
                .duration(60)
                .appointmentDesc("Obezite")
                .doctorId(1)
                .patientId(1)
                .build();

        AppointmentResponse response = AppointmentResponse.builder()
                .dateTime(LocalDateTime.now())
                .duration(60)
                .appointmentDesc("Obezite")
                .doctor(doctor)
                .patient(patient)
                .build();

        when(appointmentService.saveAppointment(request)).thenReturn(response);

        mockMvc.perform(post("/v1/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.duration").value(request.getDuration()));
    }

    @Test
    void itShouldGetAppointmentWithId1() throws Exception {

        Integer id = 1;

        AppointmentResponse response = AppointmentResponse.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .duration(120)
                .appointmentDesc("Bel kayması")
                .build();

        when(appointmentService.getAppointmentById(id)).thenReturn(response);

        mockMvc.perform(get("/v1/appointment/{id}", 1))
                .andDo(print())
                .andExpect(jsonPath("$.duration").value(120));
    }

    @Test
    void itShouldDeleteAppointmentWithId20() throws Exception {

        Integer id = 20;

        SaveAppointmentRequest request = SaveAppointmentRequest.builder()
                .dateTime(LocalDateTime.now())
                .duration(45)
                .appointmentDesc("Bel kırığı")
                .build();

        AppointmentResponse response = AppointmentResponse.builder()
                .id(20)
                .dateTime(LocalDateTime.now())
                .duration(45)
                .appointmentDesc("Bel kırığı")
                .build();

        appointmentService.saveAppointment(request);

        mockMvc.perform(delete("/v1/appointment/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetAllAppointments() throws Exception {

        List<Appointment> appointmentList = appointmentService.getAppointments();

        int size = appointmentList.size();

        when(appointmentService.getAppointments()).thenReturn(appointmentList);

        mockMvc.perform(get("/v1/appointment"))
                .andExpect(jsonPath("$", hasSize(size)));
    }

    @Test
    void itShouldEditAppointment() throws Exception {

        SaveAppointmentRequest request1 = SaveAppointmentRequest.builder()
                .dateTime(LocalDateTime.now())
                .duration(45)
                .appointmentDesc("Obezite")
                .build();

        appointmentService.saveAppointment(request1);

        EditAppointmentRequest request2 = EditAppointmentRequest.builder()
                .dateTime(LocalDateTime.now())
                .duration(65)
                .appointmentDesc("Obezite")
                .build();

        AppointmentResponse response = AppointmentResponse.builder()
                .id(10)
                .dateTime(LocalDateTime.now())
                .duration(65)
                .appointmentDesc("Obezite")
                .build();

        Integer id = 10;

        when(appointmentService.editAppointment(10, request2)).thenReturn(response);

        mockMvc.perform(put("/v1/appointment/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeJson(request2)))
                .andDo(print())
                .andExpect(jsonPath("$.duration").value(65));
    }

    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}