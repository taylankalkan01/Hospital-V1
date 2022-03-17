package com.example.Hospital.dto;

import com.example.Hospital.model.City;
import com.example.Hospital.model.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PatientDto {

    private String id;
    private String name;
    private Gender gender;
    private Integer dateOfBirth;
    private City city;
    private String address;
    private boolean healthInsurance;
}
