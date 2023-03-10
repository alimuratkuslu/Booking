package com.alimurat.booking;
import com.alimurat.booking.model.Doctor;
import com.alimurat.booking.model.Patient;
import com.alimurat.booking.model.Role;
import com.alimurat.booking.repository.DoctorRepository;
import com.alimurat.booking.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

	private final PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;

	private final PasswordEncoder passwordEncoder;

	public BookingApplication(PatientRepository patientRepository, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Patient patient1 = patientRepository.save(Patient.builder()
						.id(1)
						.name("Ali Murat")
						.surname("Kuşlu")
						.email("ali@gmail.com")
						.birthDate(LocalDate.of(2001, 01, 02))
						.password(passwordEncoder.encode("alipassword"))
						.role(Role.PATIENT)
						.isActive(true)
				.build());

		Patient patient2 = patientRepository.save(Patient.builder()
				.id(2)
				.name("Şükran")
				.surname("Atan")
				.email("sukran@gmail.com")
				.birthDate(LocalDate.of(2001, 03, 07))
				.password(passwordEncoder.encode("sukranpassword"))
						.role(Role.PATIENT)
				.isActive(true)
				.build());

		Doctor doctor1 = doctorRepository.save(Doctor.builder()
						.id(1)
						.name("Doğan")
						.surname("Kuşlu")
						.email("dogan@gmail.com")
						.field("Diyetisyen")
						.password(passwordEncoder.encode("doganpassword"))
						.role(Role.DOCTOR)
				.build());

		Doctor doctor2 = doctorRepository.save(Doctor.builder()
				.id(2)
				.name("Ege")
				.surname("Çelik")
				.email("ege@gmail.com")
				.field("Fizyoloji")
				.password(passwordEncoder.encode("egepassword"))
				.role(Role.DOCTOR)
				.build());

		System.out.println(patient1);
		System.out.println(patient2);
		System.out.println(doctor1);
		System.out.println(doctor2);
	}
}
