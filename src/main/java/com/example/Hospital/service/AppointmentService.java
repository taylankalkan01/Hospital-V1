package com.example.Hospital.service;

import com.example.Hospital.dto.AppointmentDto;
import com.example.Hospital.dto.converter.AppointmentDtoConverter;
import com.example.Hospital.dto.request.CreateAppointmentRequest;
import com.example.Hospital.dto.request.UpdateAppointmentRequest;
import com.example.Hospital.exception.AppointmentNotFoundException;
import com.example.Hospital.model.Appointment;
import com.example.Hospital.repos.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoConverter appointmentDtoConverter;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentDtoConverter appointmentDtoConverter) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDtoConverter = appointmentDtoConverter;
    }

    public AppointmentDto createAppointment(CreateAppointmentRequest createAppointmentRequest){
        Appointment appointment = new Appointment();
        appointment.setId(createAppointmentRequest.getId());
        appointment.setDoctorId(createAppointmentRequest.getDoctorId());
        appointment.setPatientId(createAppointmentRequest.getPatientId());
        appointment.setDate(createAppointmentRequest.getDate());
        appointment.setHour(createAppointmentRequest.getHour());
        appointment.setMinute(createAppointmentRequest.getMinute());
        appointment.setNotes(createAppointmentRequest.getNotes());

        appointmentRepository.save(appointment);
        return appointmentDtoConverter.convert(appointment);
    }

    public List<AppointmentDto> getAllAppointments(){
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Integer id){
        return appointmentRepository.findById(id)
                .map(appointmentDtoConverter::convert)
                .orElseThrow(()->new AppointmentNotFoundException("Not found!"));
    }

    public AppointmentDto getAppointmentByDoctorId(String doctorId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findByDoctorId(doctorId);
        return appointmentOptional.map(appointmentDtoConverter::convert).orElse(new AppointmentDto());
    }

    public AppointmentDto getAppointmentByPatientId(String patientId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findByPatientId(patientId);
        return appointmentOptional.map(appointmentDtoConverter::convert).orElse(new AppointmentDto());
    }

    public void deleteAppointment(Integer id){
        appointmentRepository.findById(id).orElseThrow(()->new AppointmentNotFoundException("Appointment id could not be found"));

        appointmentRepository.deleteById(id);
    }

    public AppointmentDto updateAppointment(Integer id, UpdateAppointmentRequest updateAppointmentRequest){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        appointmentOptional.ifPresent(appointment -> {
            appointment.setDoctorId(updateAppointmentRequest.getDoctorId());
            appointment.setDate(updateAppointmentRequest.getDate());
            appointment.setHour(updateAppointmentRequest.getHour());
            appointment.setMinute(updateAppointmentRequest.getMinute());
            appointment.setNotes(updateAppointmentRequest.getNotes());

            appointmentRepository.save(appointment);
        });
        return appointmentOptional.map(appointmentDtoConverter::convert).orElse(new AppointmentDto());
    }
}
