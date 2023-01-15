package com.alimurat.booking.repository;

import com.alimurat.booking.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("SELECT d FROM Doctor d WHERE d.email = ?1")
    Doctor findByEmail(String email);
}
