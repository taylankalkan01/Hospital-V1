package com.example.Hospital.dto.request;

import com.example.Hospital.model.City;
import com.example.Hospital.model.Gender;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasePatientRequest {

    private String name;
    private Gender gender;
    private Integer dateOfBirth;
    private City city;
    private String address;
    private boolean healthInsurance;
}
