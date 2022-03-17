package com.example.Hospital.service;

import com.example.Hospital.TestSupport;
import com.example.Hospital.dto.DoctorDto;
import com.example.Hospital.dto.converter.DoctorDtoConverter;
import com.example.Hospital.dto.request.CreateDoctorRequest;
import com.example.Hospital.dto.request.UpdateDoctorRequest;
import com.example.Hospital.exception.DoctorNotFoundException;
import com.example.Hospital.model.Doctor;
import com.example.Hospital.repos.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import java.util.List;

import static com.example.Hospital.model.Department.RADIOLOGISTS;
import static com.example.Hospital.model.NamePrefix.PHD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoctorServiceTest extends TestSupport {

    private DoctorService doctorService;

    private DoctorRepository doctorRepository;
    private DoctorDtoConverter doctorDtoConverter;



    @BeforeEach
    void setUp() {
        doctorDtoConverter = Mockito.mock(DoctorDtoConverter.class);
        doctorRepository = Mockito.mock(DoctorRepository.class);

        doctorService = new DoctorService(doctorRepository,doctorDtoConverter);
    }

    @Test
    void testCreateDoctor_whenCreateDoctorRequestIsValid_thenReturnDoctorDto(){
        CreateDoctorRequest createDoctorRequest = generateCreateDoctorRequest();
        Doctor doctor = generateDoctor();
        DoctorDto doctorDto = generateDoctorDto();

        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        Mockito.when(doctorDtoConverter.convert(doctor)).thenReturn(doctorDto);

        DoctorDto result = doctorService.createDoctor(createDoctorRequest);

        assertEquals(doctorDto,result);

        Mockito.verify(doctorRepository).save(doctor);
        Mockito.verify(doctorDtoConverter).convert(doctor);
    }

    @Test
    void testGetAllDoctors_thenReturnDoctorDto(){
        Doctor doctor = generateDoctor();
        DoctorDto doctorDto = generateDoctorDto();
        List<Doctor> doctorList = generateDoctorList();
        List<DoctorDto> doctorDtoList = generateDoctorDtoList();

        Mockito.when(doctorRepository.findAll()).thenReturn(doctorList);
        Mockito.when(doctorDtoConverter.convert(doctor)).thenReturn(doctorDto);

        List<DoctorDto> result = doctorService.getAllDoctors();
        assertEquals(doctorDtoList,result);

        Mockito.verify(doctorRepository).findAll();
        Mockito.verify(doctorDtoConverter).convert(doctor);
    }

    @Test
    void testDoctorById_whenDoctorIdExist_thenReturnDoctorDto(){
        Doctor doctor = generateDoctor();
        DoctorDto doctorDto = generateDoctorDto();

        Mockito.when(doctorRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(doctor));
        Mockito.when(doctorDtoConverter.convert(doctor)).thenReturn(doctorDto);

        DoctorDto result = doctorService.getDoctorById("id");
        assertEquals(doctorDto, result);

        Mockito.verify(doctorRepository).findById("id");
        Mockito.verify(doctorDtoConverter).convert(doctor);
    }

    @Test
    void testDoctorById_whenDoctorIdNotExist_thenThrowDoctorNotFoundException(){

        Mockito.when(doctorRepository.findById("id")).thenThrow(DoctorNotFoundException.class);

        assertThrows(DoctorNotFoundException.class, ()-> doctorService.getDoctorById("id"));
        Mockito.verify(doctorRepository).findById("id");
    }

    @Test
    void testUpdateDoctor_whenDoctorIdExist_thenReturnDoctorDto(){
        Doctor doctor = generateDoctor();
        DoctorDto doctorDto = generateDoctorDto();
        UpdateDoctorRequest updateDoctorRequest = generateUpdateDoctorRequest();

        Mockito.when(doctorRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(doctor));
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        Mockito.when(doctorDtoConverter.convert(doctor)).thenReturn(doctorDto);

        DoctorDto result = doctorService.updateDoctor("id",updateDoctorRequest);
        assertEquals(doctorDto,result);

        Mockito.verify(doctorRepository).findById("id");
        Mockito.verify(doctorRepository).save(doctor);
        Mockito.verify(doctorDtoConverter).convert(doctor);

    }

    @Test
    void testUpdateDoctor_whenDoctorIdNotExists_thenThrowDoctorNotFoundException(){
        UpdateDoctorRequest updateDoctorRequest =generateUpdateDoctorRequest();

        Mockito.when(doctorRepository.findById("id")).thenThrow(DoctorNotFoundException.class);

        assertThrows(DoctorNotFoundException.class,()->doctorService.updateDoctor("id",updateDoctorRequest));

        Mockito.verify(doctorRepository).findById("id");
        Mockito.verifyNoInteractions(doctorDtoConverter);
    }

    @Test
    void testDeleteDoctor_whenDoctorIdExist_thenReturnVoid(){
        Doctor doctor = generateDoctor();

        Mockito.when(doctorRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(doctor));

        doctorService.deleteDoctor("id");

        Mockito.verify(doctorRepository).deleteById("id");
    }

    @Test
    void testDeleteDoctor_whenDoctorIdNotExist_thenThrowDoctorNotFoundException(){

        Mockito.when(doctorRepository.findById("id")).thenThrow(DoctorNotFoundException.class);

        assertThrows(DoctorNotFoundException.class,()->doctorService.deleteDoctor("id"));

        Mockito.verify(doctorRepository).findById("id");
    }





}