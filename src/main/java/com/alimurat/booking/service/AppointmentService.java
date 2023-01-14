package com.alimurat.booking.service;

import com.alimurat.booking.dto.AppointmentResponse;
import com.alimurat.booking.dto.EditAppointmentRequest;
import com.alimurat.booking.dto.SaveAppointmentRequest;
import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.repository.AppointmentRepository;
import com.alimurat.booking.repository.DoctorRepository;
import com.alimurat.booking.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    public final AppointmentRepository appointmentRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<AppointmentResponse> getAppointments(){

        return appointmentRepository.findAll().stream().map(model ->
            AppointmentResponse.builder()
                    .id(model.getId())
                    .dateTime(model.getDateTime())
                    .duration(model.getDuration())
                    .appointmentDesc(model.getAppointmentDesc())
                    .doctor(model.getDoctor())
                    .patient(model.getPatient())
                    .build()
        ).collect(Collectors.toList());
    }

    public AppointmentResponse getAppointmentById(Integer id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(RuntimeException::new);

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .dateTime(appointment.getDateTime())
                .duration(appointment.getDuration())
                .appointmentDesc(appointment.getAppointmentDesc())
                .doctor(appointment.getDoctor())
                .patient(appointment.getPatient())
                .build();
    }

    public AppointmentResponse saveAppointment(SaveAppointmentRequest request){
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow(RuntimeException::new);
        Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow(RuntimeException::new);

        Appointment appointment = Appointment.builder()
                .dateTime(request.getDateTime())
                .duration(request.getDuration())
                .appointmentDesc(request.getAppointmentDesc())
                .doctor(doctor)
                .patient(patient)
                .build();

        final Appointment fromDbAppointment = appointmentRepository.save(appointment);

        return AppointmentResponse.builder()
                .id(fromDbAppointment.getId())
                .dateTime(fromDbAppointment.getDateTime())
                .duration(fromDbAppointment.getDuration())
                .appointmentDesc(fromDbAppointment.getAppointmentDesc())
                .doctor(fromDbAppointment.getDoctor())
                .patient(fromDbAppointment.getPatient())
                .build();
    }

    public AppointmentResponse editAppointment(Integer id, EditAppointmentRequest request){

        Appointment currentAppointment = appointmentRepository.findById(id).orElseThrow(RuntimeException::new);
        currentAppointment.setDateTime(request.getDateTime());
        currentAppointment.setDuration(request.getDuration());
        currentAppointment.setAppointmentDesc(request.getAppointmentDesc());
        currentAppointment.setDoctor(request.getDoctor());
        currentAppointment.setPatient(request.getPatient());

        appointmentRepository.save(currentAppointment);

        return AppointmentResponse.builder()
                .id(currentAppointment.getId())
                .dateTime(currentAppointment.getDateTime())
                .duration(currentAppointment.getDuration())
                .appointmentDesc(currentAppointment.getAppointmentDesc())
                .doctor(currentAppointment.getDoctor())
                .patient(currentAppointment.getPatient())
                .build();
    }

    public void deleteAppointmentById(Integer id){
        appointmentRepository.deleteById(id);
    }
}
