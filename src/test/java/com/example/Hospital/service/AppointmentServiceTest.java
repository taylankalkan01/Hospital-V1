package com.example.Hospital.service;

import com.example.Hospital.TestSupport;
import com.example.Hospital.dto.AppointmentDto;
import com.example.Hospital.dto.converter.AppointmentDtoConverter;
import com.example.Hospital.dto.request.CreateAppointmentRequest;
import com.example.Hospital.dto.request.UpdateAppointmentRequest;
import com.example.Hospital.exception.AppointmentNotFoundException;
import com.example.Hospital.model.Appointment;
import com.example.Hospital.model.Doctor;
import com.example.Hospital.repos.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest extends TestSupport {
    private AppointmentService appointmentService;

    private AppointmentRepository appointmentRepository;
    private AppointmentDtoConverter appointmentDtoConverter;



    @BeforeEach
    void setUp() {
        appointmentDtoConverter = Mockito.mock(AppointmentDtoConverter.class);
        appointmentRepository = Mockito.mock(AppointmentRepository.class);

        appointmentService = new AppointmentService(appointmentRepository,appointmentDtoConverter);
    }

    @Test
    void testCreateAppointment_whenCreateAppointmentRequestIsValid_thenReturnAppointmentDto(){
        CreateAppointmentRequest createAppointmentRequest = generateCreateAppointmentRequest();
        Appointment appointment = generateAppointment();
        AppointmentDto appointmentDto = generateAppointmentDto();

        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Mockito.when(appointmentDtoConverter.convert(appointment)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.createAppointment(createAppointmentRequest);

        assertEquals(appointmentDto,result);

        Mockito.verify(appointmentRepository).save(appointment);
        Mockito.verify(appointmentDtoConverter).convert(appointment);
    }

    @Test
    void testGetAllAppointments_thenReturnAppointmentDto(){
       Appointment appointment=generateAppointment();
       AppointmentDto appointmentDto = generateAppointmentDto();
       List<Appointment> appointmentList =generateAppointmentList();
       List<AppointmentDto> appointmentDtoList=generateAppointmentDtoList();

        Mockito.when(appointmentRepository.findAll()).thenReturn(appointmentList);
        Mockito.when(appointmentDtoConverter.convert(appointment)).thenReturn(appointmentDto);

        List<AppointmentDto> result = appointmentService.getAllAppointments();
        assertEquals(appointmentDtoList,result);

        Mockito.verify(appointmentRepository).findAll();
        Mockito.verify(appointmentDtoConverter).convert(appointment);
    }

    @Test
    void testGetAppointmentById_whenAppointmentIdExist_thenReturnAppointmentDto(){
        Appointment appointment = generateAppointment();
        AppointmentDto appointmentDto = generateAppointmentDto();

        Mockito.when(appointmentRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(appointment));
        Mockito.when(appointmentDtoConverter.convert(appointment)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.getAppointmentById(1);
        assertEquals(appointmentDto, result);

        Mockito.verify(appointmentRepository).findById(1);
        Mockito.verify(appointmentDtoConverter).convert(appointment);
    }

    @Test
    void testGetAppointmentById_whenAppointmentIdNotExist_thenThrowAppointmentNotFoundException(){

        Mockito.when(appointmentRepository.findById(1)).thenThrow(AppointmentNotFoundException.class);

        assertThrows(AppointmentNotFoundException.class, ()-> appointmentService.getAppointmentById(1));
        Mockito.verify(appointmentRepository).findById(1);
    }

    @Test
    void testGetAppointmentByDoctorId_whenDoctorIdExist_thenReturnAppointmentDto(){
        Appointment appointment = generateAppointment();
        AppointmentDto appointmentDto = generateAppointmentDto();
        Doctor doctor = generateDoctor();

        Mockito.when(appointmentRepository.findByDoctorId("id")).thenReturn(java.util.Optional.ofNullable(appointment));
        Mockito.when(appointmentDtoConverter.convert(appointment)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.getAppointmentById(1);
        assertEquals(appointmentDto, result);

        Mockito.verify(appointmentRepository).findById(1);
        Mockito.verify(appointmentDtoConverter).convert(appointment);

    }

    @Test
    void testUpdateAppointment_whenAppointmentIdExist_thenReturnAppointmentDto(){
        Appointment appointment = generateAppointment();
        AppointmentDto appointmentDto = generateAppointmentDto();
        UpdateAppointmentRequest updateAppointmentRequest = generateUpdateAppointmentRequest();

        Mockito.when(appointmentRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(appointment));
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Mockito.when(appointmentDtoConverter.convert(appointment)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.updateAppointment(1,updateAppointmentRequest);
        assertEquals(appointmentDto,result);

        Mockito.verify(appointmentRepository).findById(1);
        Mockito.verify(appointmentRepository).save(appointment);
        Mockito.verify(appointmentDtoConverter).convert(appointment);

    }

    @Test
    void testUpdateAppointment_whenAppointmentIdNotExists_thenThrowAppointmentNotFoundException(){
        UpdateAppointmentRequest updateAppointmentRequest = generateUpdateAppointmentRequest();

        Mockito.when(appointmentRepository.findById(1)).thenThrow(AppointmentNotFoundException.class);

        assertThrows(AppointmentNotFoundException.class,()->appointmentService.updateAppointment(1,updateAppointmentRequest));

        Mockito.verify(appointmentRepository).findById(1);
        Mockito.verifyNoInteractions(appointmentDtoConverter);
    }

    @Test
    void testDeleteAppointment_whenAppointmentIdExist_thenReturnVoid(){
        Appointment appointment = generateAppointment();
        Mockito.when(appointmentRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(appointment));

        appointmentService.deleteAppointment(1);

        Mockito.verify(appointmentRepository).deleteById(1);
    }

    @Test
    void testDeleteAppointment_whenAppointmentIdNotExist_thenThrowAppointmentNotFoundException(){

        Mockito.when(appointmentRepository.findById(1)).thenThrow(AppointmentNotFoundException.class);

        assertThrows(AppointmentNotFoundException.class,()->appointmentService.deleteAppointment(1));

        Mockito.verify(appointmentRepository).findById(1);
    }






}