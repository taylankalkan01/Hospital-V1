package com.example.Hospital.dto.request;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateDoctorRequest extends BaseDoctorRequest {
    private String id;


}
