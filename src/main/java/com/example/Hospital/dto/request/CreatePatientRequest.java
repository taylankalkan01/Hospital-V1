package com.example.Hospital.dto.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientRequest extends BasePatientRequest {
    private String id;
}
