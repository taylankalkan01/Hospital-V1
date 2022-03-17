package com.example.Hospital.service;

import com.example.Hospital.dto.converter.DoctorDtoConverter;
import com.example.Hospital.dto.request.CreateDoctorRequest;
import com.example.Hospital.dto.DoctorDto;
import com.example.Hospital.dto.request.UpdateDoctorRequest;
import com.example.Hospital.exception.DoctorNotFoundException;
import com.example.Hospital.model.Doctor;
import org.springframework.stereotype.Service;
import com.example.Hospital.repos.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorDtoConverter doctorDtoConverter;

    public DoctorService(DoctorRepository doctorRepository, DoctorDtoConverter doctorDtoConverter) {
        this.doctorRepository = doctorRepository;
        this.doctorDtoConverter = doctorDtoConverter;
    }


    public DoctorDto createDoctor(CreateDoctorRequest createDoctorRequest){
        Doctor doctor = new Doctor();
        doctor.setId(createDoctorRequest.getId());
        doctor.setName(createDoctorRequest.getName());
        doctor.setNamePrefix(createDoctorRequest.getNamePrefix());
        doctor.setDepartment(createDoctorRequest.getDepartment());
        doctor.setDateOfGraduate(createDoctorRequest.getDateOfGraduate());
        doctor.setDateOfStart(createDoctorRequest.getDateOfStart());

        doctorRepository.save(doctor);
        return doctorDtoConverter.convert(doctor);
    }

    public List<DoctorDto> getAllDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = new ArrayList<>();

        for (Doctor doctor : doctorList){
            doctorDtoList.add(doctorDtoConverter.convert(doctor));
        }
        return doctorDtoList;
    }

    public DoctorDto getDoctorById(String id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        return doctorOptional
                .map(doctorDtoConverter::convert)
                .orElse(new DoctorDto());
    }

    public void deleteDoctor(String id){
        doctorRepository.findById(id)
                .orElseThrow(()-> new DoctorNotFoundException("Doctor id could not be found"));

        doctorRepository.deleteById(id);
    }

    public DoctorDto updateDoctor(String id, UpdateDoctorRequest updateDoctorRequest){


        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        doctorOptional.ifPresent(doctor -> {
            doctor.setName(updateDoctorRequest.getName());
            doctor.setNamePrefix(updateDoctorRequest.getNamePrefix());
            doctor.setDepartment(updateDoctorRequest.getDepartment());
            doctor.setDateOfGraduate(updateDoctorRequest.getDateOfGraduate());
            doctor.setDateOfStart(updateDoctorRequest.getDateOfStart());

            doctorRepository.save(doctor);

        });
        return doctorOptional.map(doctorDtoConverter::convert).orElse(new DoctorDto());
        }


    }

