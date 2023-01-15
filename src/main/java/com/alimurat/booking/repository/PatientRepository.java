package com.alimurat.booking.repository;

import com.alimurat.booking.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p WHERE p.email = ?1")
    Patient findByEmail(String email);
}
