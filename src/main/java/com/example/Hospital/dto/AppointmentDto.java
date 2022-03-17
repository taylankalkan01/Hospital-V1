package com.example.Hospital.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class AppointmentDto {
    private Integer id;
    private String doctorId;
    private String patientId;
    private String date;
    private Integer hour;
    private Integer minute;
    private String notes;
}
