package com.example.Hospital.dto.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseAppointmentRequest {

    private String doctorId;
    private String date;
    private Integer hour;
    private Integer minute;
    private String notes;
}
