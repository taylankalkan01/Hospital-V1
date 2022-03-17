package com.example.Hospital.dto;

import com.example.Hospital.model.Department;
import com.example.Hospital.model.NamePrefix;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DoctorDto {
    private String id;
    private String name;
    private NamePrefix namePrefix;
    private Department department;
    private Integer dateOfGraduate;
    private Integer dateOfStart;
}
