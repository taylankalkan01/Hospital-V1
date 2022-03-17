package com.example.Hospital.dto.converter;

import com.example.Hospital.dto.DoctorDto;
import com.example.Hospital.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorDtoConverter {
    public DoctorDto convert(Doctor doctor){
        return DoctorDto.builder()
                       .id(doctor.getId())
                       .name(doctor.getName())
                       .namePrefix(doctor.getNamePrefix())
                       .dateOfGraduate(doctor.getDateOfGraduate())
                       .dateOfStart(doctor.getDateOfStart())
                .build();
    }
}
