package com.alimurat.booking.repository;

import com.alimurat.booking.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = ?1")
    List<Appointment> findByDoctorId(Integer id);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = ?1")
    List<Appointment> findByPatientId(Integer id);
}
