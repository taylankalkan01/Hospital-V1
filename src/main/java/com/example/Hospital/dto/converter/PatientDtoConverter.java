package com.example.Hospital.dto.converter;

import com.example.Hospital.dto.PatientDto;
import com.example.Hospital.model.Patient;

import org.springframework.stereotype.Component;

@Component
public class PatientDtoConverter {

    public PatientDto convert(Patient patient) {

        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .city(patient.getCity())
                .address(patient.getAddress())
                .healthInsurance(patient.isHealthInsurance())
                .build();
    }
}
