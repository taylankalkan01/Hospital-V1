package com.example.Hospital.dto.request;

import com.example.Hospital.model.Department;
import com.example.Hospital.model.NamePrefix;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseDoctorRequest {
    private String name;
    private NamePrefix namePrefix;
    private Department department;
    private Integer dateOfGraduate;
    private Integer dateOfStart;
}
