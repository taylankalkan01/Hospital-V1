package com.example.Hospital.controller;

import com.example.Hospital.dto.DoctorDto;
import com.example.Hospital.dto.request.CreateDoctorRequest;
import com.example.Hospital.dto.request.UpdateDoctorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Hospital.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest){
        return ResponseEntity.ok().body(doctorService.createDoctor(createDoctorRequest));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors(){
        return ResponseEntity.ok().body(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(doctorService.getDoctorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") String id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable String id,
                                                      @RequestBody UpdateDoctorRequest doctorRequest){
        return ResponseEntity.ok().body(doctorService.updateDoctor(id,doctorRequest));
    }
}
