package com.alimurat.booking;

import com.alimurat.booking.model.Appointment;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    private static Integer userId = 1;

    public static List<Patient> generatePatients(){
        return IntStream.range(1, 5).mapToObj(i ->
            new Patient(i,
                    "name" + i,
                    "surname" + i,
                    "email" + i,
                    LocalDate.now(),
                    new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public static Patient generatePatient(){
        return new Patient(userId,
                "name" + userId,
                "surname" + userId,
                "email" + userId,
                LocalDate.now(),
                true);
    }
}
