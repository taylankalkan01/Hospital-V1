package com.example.Hospital.service;

import com.example.Hospital.dto.PatientDto;
import com.example.Hospital.dto.converter.PatientDtoConverter;
import com.example.Hospital.dto.request.CreatePatientRequest;
import com.example.Hospital.dto.request.UpdatePatientRequest;
import com.example.Hospital.exception.PatientNotFoundException;
import com.example.Hospital.model.Patient;
import com.example.Hospital.repos.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientDtoConverter patientDtoConverter;

    public PatientService(PatientRepository patientRepository, PatientDtoConverter patientDtoConverter) {
        this.patientRepository = patientRepository;
        this.patientDtoConverter = patientDtoConverter;
    }

    public PatientDto createPatient(CreatePatientRequest createPatientRequest){
        Patient patient = new Patient();
        patient.setId(createPatientRequest.getId());
        patient.setName(createPatientRequest.getName());
        patient.setGender(createPatientRequest.getGender());
        patient.setDateOfBirth(createPatientRequest.getDateOfBirth());
        patient.setCity(createPatientRequest.getCity());
        patient.setAddress(createPatientRequest.getAddress());
        patient.setHealthInsurance(createPatientRequest.isHealthInsurance());

        patientRepository.save(patient);
        return patientDtoConverter.convert(patient);
    }

    public List<PatientDto> getAllPatients(){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDto> patientDtoList = new ArrayList<>();

        for (Patient patient: patientList){
            patientDtoList.add(patientDtoConverter.convert(patient));
        }
        return patientDtoList;
    }

    public PatientDto getPatientById(String id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.map(patientDtoConverter::convert).orElse(new PatientDto());
    }

    public void deletePatient(String id){
        patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient id could not be found"));

        patientRepository.deleteById(id);
    }

    public PatientDto updatePatient(String id, UpdatePatientRequest updatePatientRequest){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        patientOptional.ifPresent(patient -> {
            patient.setName(updatePatientRequest.getName());
            patient.setGender(updatePatientRequest.getGender());
            patient.setDateOfBirth(updatePatientRequest.getDateOfBirth());
            patient.setCity(updatePatientRequest.getCity());
            patient.setAddress(updatePatientRequest.getAddress());
            patient.setHealthInsurance(updatePatientRequest.isHealthInsurance());
            patientRepository.save(patient);
        });
        return patientOptional.map(patientDtoConverter::convert).orElse(new PatientDto());
    }
}
