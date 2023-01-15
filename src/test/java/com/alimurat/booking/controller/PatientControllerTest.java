package com.alimurat.booking.controller;

import com.alimurat.booking.dto.DoctorOfPatientDto;
import com.alimurat.booking.dto.PatientResponse;
import com.alimurat.booking.dto.SavePatientRequest;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "integration")
class PatientControllerTest {

    @MockBean
    private PatientService patientService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldSavePatient() throws Exception {

        SavePatientRequest request = SavePatientRequest.builder()
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(null)
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .doctor(null)
                .appointments(null)
                .build();

        when(patientService.savePatient(request)).thenReturn(response);

        mockMvc.perform(post("/v1/patient")
                        .contentType(APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    void itShouldGetAllPatients() throws Exception {

        List<Patient> patientList = patientService.getPatients();

        int size = patientList.size();

        when(patientService.getPatients()).thenReturn(patientList);

        mockMvc.perform(get("/v1/patient"))
                .andExpect(jsonPath("$", hasSize(size)));
    }

    @Test
    void shouldShowDoctorOfPatient() throws Exception {
        Integer id = 1;

        DoctorOfPatientDto dof = DoctorOfPatientDto.builder()
                .name("testpname")
                .surname("testpsurname")
                .email("testpemail")
                .birthDate(LocalDate.now())
                .doctorName("testdname")
                .doctorSurname("testdsurname")
                .doctorEmail("testdemail")
                .doctorField("testdfield")
                .build();

        when(patientService.showDoctor(id)).thenReturn(dof);

        mockMvc.perform(get("/v1/patient/doctor/{id}", 1))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(dof.getName()));
    }

    @Test
    void itShouldGetPatientWithId1() throws Exception {
        Integer id = 1;

        PatientResponse response = PatientResponse.builder()
                .id(1)
                .build();

        when(patientService.getPatientById(id)).thenReturn(response);

        mockMvc.perform(get("/v1/patient/{id}", 1))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void shouldUpdatePatient() throws Exception {

        SavePatientRequest request = SavePatientRequest.builder()
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(null)
                .name("testnameedited")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .doctor(null)
                .appointments(null)
                .build();

        when(patientService.editPatient(1, request)).thenReturn(response);

        mockMvc.perform(put("/v1/patient/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(response.getName()));
    }

    @Test
    void itShouldChangeStatusOfPatientToFalse() throws Exception {
        SavePatientRequest request = SavePatientRequest.builder()
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(125)
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .isActive(true)
                .doctor(null)
                .appointments(null)
                .build();

        when(patientService.savePatient(request)).thenReturn(response);
        patientService.deactivatePatient(response.getId());

        verify(patientService).deactivatePatient(125);

        mockMvc.perform(patch("/v1/patient/{id}", 125))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void itShouldChangeStatusOfPatientToTrue() throws Exception {
        SavePatientRequest request = SavePatientRequest.builder()
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(125)
                .name("testname")
                .surname("testsurname")
                .email("testemail")
                .birthDate(LocalDate.now())
                .password("testpassword")
                .isActive(false)
                .doctor(null)
                .appointments(null)
                .build();

        when(patientService.savePatient(request)).thenReturn(response);
        patientService.activatePatient(response.getId());

        mockMvc.perform(patch("/v1/patient/activate/{id}", 125))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void itShouldDeletePatientWithId100() throws Exception {
        Integer id = 100;

        SavePatientRequest request = SavePatientRequest.builder()
                .name("patientname")
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(100)
                .build();

        patientService.savePatient(request);

        mockMvc.perform(delete("/v1/patient/{id}", 100))
                .andExpect(status().isOk());
    }

    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}