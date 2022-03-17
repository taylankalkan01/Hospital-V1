package com.example.Hospital.service;



import com.example.Hospital.TestSupport;
import com.example.Hospital.dto.PatientDto;
import com.example.Hospital.dto.converter.PatientDtoConverter;
import com.example.Hospital.dto.request.CreatePatientRequest;
import com.example.Hospital.dto.request.UpdatePatientRequest;
import com.example.Hospital.exception.PatientNotFoundException;
import com.example.Hospital.model.Patient;
import com.example.Hospital.repos.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceTest extends TestSupport {
    private PatientService patientService;

    private PatientRepository patientRepository;
    private PatientDtoConverter patientDtoConverter;



    @BeforeEach
    void setUp() {
        patientDtoConverter = Mockito.mock(PatientDtoConverter.class);
        patientRepository = Mockito.mock(PatientRepository.class);

        patientService = new PatientService(patientRepository,patientDtoConverter);
    }

    @Test
    void testCreatePatient_whenCreatePatientRequestIsValid_thenReturnPatientDto(){
        CreatePatientRequest createPatientRequest = generateCreatePatientRequest();
        Patient patient = generatePatient();
        PatientDto patientDto = generatePatientDto();

        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Mockito.when(patientDtoConverter.convert(patient)).thenReturn(patientDto);

        PatientDto result = patientService.createPatient(createPatientRequest);

        assertEquals(patientDto,result);

        Mockito.verify(patientRepository).save(patient);
        Mockito.verify(patientDtoConverter).convert(patient);
    }

    @Test
    void testGetAllPatients_thenReturnPatientDto(){
        Patient patient = generatePatient();
        PatientDto patientDto = generatePatientDto();
        List<Patient> patientList = generatePatientList();
        List<PatientDto> patientDtoList = generatePatientDtoList();

        Mockito.when(patientRepository.findAll()).thenReturn(patientList);
        Mockito.when(patientDtoConverter.convert(patient)).thenReturn(patientDto);

        List<PatientDto> result = patientService.getAllPatients();
        assertEquals(patientDtoList,result);

        Mockito.verify(patientRepository).findAll();
        Mockito.verify(patientDtoConverter).convert(patient);
    }

    @Test
    void testPatientById_whenPatientIdExist_thenReturnPatientDto(){
        Patient patient = generatePatient();
        PatientDto patientDto = generatePatientDto();

        Mockito.when(patientRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(patient));
        Mockito.when(patientDtoConverter.convert(patient)).thenReturn(patientDto);

        PatientDto result = patientService.getPatientById("id");
        assertEquals(patientDto, result);

        Mockito.verify(patientRepository).findById("id");
        Mockito.verify(patientDtoConverter).convert(patient);
    }

    @Test
    void testPatientById_whenPatientIdNotExist_thenThrowPatientNotFoundException(){

        Mockito.when(patientRepository.findById("id")).thenThrow(PatientNotFoundException.class);

        assertThrows(PatientNotFoundException.class, ()-> patientService.getPatientById("id"));
        Mockito.verify(patientRepository).findById("id");
    }

    @Test
    void testUpdatePatient_whenPatientIdExist_thenReturnPatientDto(){
        Patient patient = generatePatient();
        PatientDto patientDto = generatePatientDto();
        UpdatePatientRequest updatePatientRequest = generateUpdatePatientRequest();

        Mockito.when(patientRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(patient));
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Mockito.when(patientDtoConverter.convert(patient)).thenReturn(patientDto);

        PatientDto result = patientService.updatePatient("id",updatePatientRequest);
        assertEquals(patientDto,result);

        Mockito.verify(patientRepository).findById("id");
        Mockito.verify(patientRepository).save(patient);
        Mockito.verify(patientDtoConverter).convert(patient);

    }

    @Test
    void testUpdatePatient_whenPatientIdNotExists_thenThrowPatientNotFoundException(){
        UpdatePatientRequest updatePatientRequest = generateUpdatePatientRequest();

        Mockito.when(patientRepository.findById("id")).thenThrow(PatientNotFoundException.class);

        assertThrows(PatientNotFoundException.class,()->patientService.updatePatient("id",updatePatientRequest));

        Mockito.verify(patientRepository).findById("id");
        Mockito.verifyNoInteractions(patientDtoConverter);
    }

    @Test
    void testDeletePatient_whenPatientIdExist_thenReturnVoid(){
        Patient patient = generatePatient();

        Mockito.when(patientRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(patient));

        patientService.deletePatient("id");

        Mockito.verify(patientRepository).deleteById("id");
    }

    @Test
    void testDeletePatient_whenPatientIdNotExist_thenThrowPatientNotFoundException(){

        Mockito.when(patientRepository.findById("id")).thenThrow(PatientNotFoundException.class);

        assertThrows(PatientNotFoundException.class,()->patientService.deletePatient("id"));

        Mockito.verify(patientRepository).findById("id");
    }





}