package com.example.Hospital.repos;

import com.example.Hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findByDoctorId(String doctorId);

    Optional<Appointment> findByPatientId(String patientId);
}
