package com.alimurat.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    private String password;

    private Boolean isActive;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient(Integer i, String s, String s1, String s2, LocalDate now, Boolean nextBoolean) {
        i = id;
        s = name;
        s1 = surname;
        s2 = email;
        now = birthDate;
        nextBoolean = isActive;
    }
}
