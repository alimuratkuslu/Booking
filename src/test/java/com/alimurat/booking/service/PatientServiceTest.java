package com.alimurat.booking.service;

import com.alimurat.booking.TestSupport;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientServiceTest extends TestSupport {

    private PatientRepository repository;

    private PatientService service;

    @BeforeEach
    public void setUp(){
        repository = Mockito.mock(PatientRepository.class);

        //service = new PatientService(repository, passwordEncoder);
    }

    @Test
    public void testGetAllPatients_itShouldReturnPatientList(){
        List<Patient> patientList = generatePatients();

        Mockito.when(repository.findAll()).thenReturn(patientList);

        List<Patient> result = service.getPatients();

        assertEquals(patientList, result);
        Mockito.verify(repository).findAll();
    }

    /*
    @Test
    public void testGetPatientWithId1_whenPatientExists_itShouldReturnPatient(){
        Patient patient = generatePatient();

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(patient));

        Optional<Patient> result = service.getPatientById(1);

        assertEquals(patient, result);
        Mockito.verify(repository).findById(1);

    }

    @Test
    public void testGetPatientWithId1_whenPatientDoesNotExist_itShouldThrowException(){
        Patient patient = generatePatient();

        Mockito.when(repository.findById(100)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getPatientById(100));

        Mockito.verify(repository).findById(100);
    }

    @Test
    public void testCreatePatient_itShouldReturnCreatedPatientDto(){

        SavePatientRequest request = SavePatientRequest.builder()
                .name("name")
                .surname("surname")
                .email("email")
                .birthDate(LocalDate.now())
                .password("password")
                .build();

        Patient patient = Patient.builder()
                .id(null)
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .password(request.getPassword())
                .doctor(null)
                .appointments(null)
                .build();

        PatientResponse response = PatientResponse.builder()
                .id(null)
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .password(request.getPassword())
                .doctor(null)
                .appointments(null)
                .build();

        Patient res = Patient.builder()
                .id(null)
                .name(response.getName())
                .surname(response.getSurname())
                .email(response.getEmail())
                .birthDate(response.getBirthDate())
                .password(response.getPassword())
                .doctor(null)
                .appointments(null)
                .build();

        Mockito.when(repository.save(patient)).thenReturn(res);

        PatientResponse result = service.savePatient(request);

        assertEquals(response, result);

        Mockito.verify(repository).save(patient);
    }
    */

}