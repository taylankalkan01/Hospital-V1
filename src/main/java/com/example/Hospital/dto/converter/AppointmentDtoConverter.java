package com.example.Hospital.dto.converter;

import com.example.Hospital.dto.AppointmentDto;
import com.example.Hospital.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDtoConverter {

    public AppointmentDto convert(Appointment appointment){
        return AppointmentDto.builder()
                       .id(appointment.getId())
                       .doctorId(appointment.getDoctorId())
                       .patientId(appointment.getPatientId())
                       .date(appointment.getDate())
                       .hour(appointment.getHour())
                       .minute(appointment.getMinute())
                       .notes(appointment.getNotes())
                .build();
    }

}
