package com.alimurat.booking.controller;

import com.alimurat.booking.dto.*;
import com.alimurat.booking.model.Doctor;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "integration")
class DoctorControllerTest {

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PatientService patientService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldSaveDoctor() throws Exception{

        SaveDoctorRequest request = SaveDoctorRequest.builder()
                .name("testdname")
                .surname("testdsurname")
                .email("testdemail")
                .field("testdfield")
                .password("testdpassword")
                .build();

        DoctorResponse response = DoctorResponse.builder()
                .id(null)
                .name("testdname")
                .surname("testdsurname")
                .email("testdemail")
                .field("testdfield")
                .password("testdpassword")
                .patients(null)
                .appointments(null)
                .build();

        when(doctorService.saveDoctor(request)).thenReturn(response);

        mockMvc.perform(post("/v1/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    void itShouldGetDoctorWithId1() throws Exception {
        Integer id = 1;

        DoctorResponse response = DoctorResponse.builder()
                .id(1)
                .build();

        when(doctorService.getDoctorById(1)).thenReturn(response);

        mockMvc.perform(get("/v1/doctor/{id}", 1))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void itShouldDeleteCustomerWithId100() throws Exception{
        Integer id = 100;

        SaveDoctorRequest request = SaveDoctorRequest.builder()
                .name("testname")
                .build();

        DoctorResponse response = DoctorResponse.builder()
                .id(100)
                .build();

        doctorService.saveDoctor(request);

        mockMvc.perform(delete("/v1/doctor/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetAllDoctors() throws Exception {

        List<Doctor> doctorList = doctorService.getDoctors();

        int size = doctorList.size();

        when(doctorService.getDoctors()).thenReturn(doctorList);

        mockMvc.perform(get("/v1/doctor"))
                .andExpect(jsonPath("$", hasSize(size)));
    }

    @Test
    void itShouldEditDoctor() throws Exception {
        SaveDoctorRequest request1 = SaveDoctorRequest.builder()
                .name("testdname")
                .surname("testdsurname")
                .build();

        doctorService.saveDoctor(request1);

        SaveDoctorRequest request2 = SaveDoctorRequest.builder()
                .name("testdeditedname")
                .surname("testdeditedsurname")
                .build();

        DoctorResponse response = DoctorResponse.builder()
                .id(150)
                .name("testdeditedname")
                .surname("testdeditedsurname")
                .build();

        Integer id = 150;

        when(doctorService.editDoctor(id, request2)).thenReturn(response);

        mockMvc.perform(put("/v1/doctor/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request2)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(request2.getName()));

    }

    @Test
    void itShouldAddPatientToDoctor() throws Exception {

        SaveDoctorRequest doctorRequest = SaveDoctorRequest.builder()
                .name("doctorName")
                .surname("doctorSurname")
                .build();

        doctorService.saveDoctor(doctorRequest);

        DoctorResponse doctorResponse = DoctorResponse.builder()
                .id(100)
                .name("doctorName")
                .surname("doctorSurname")
                .build();

        SavePatientRequest patientRequest = SavePatientRequest.builder()
                .name("patientName")
                .surname("patientSurname")
                .build();

        patientService.savePatient(patientRequest);

        PatientResponse patientResponse = PatientResponse.builder()
                .id(200)
                .name("patientName")
                .surname("patientSurname")
                .build();

        AddPatientDto request = AddPatientDto.builder()
                .doctorId(100)
                .patientId(200)
                .build();

        mockMvc.perform(post("/v1/doctor/addPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andExpect(status().isOk());

    }

    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}