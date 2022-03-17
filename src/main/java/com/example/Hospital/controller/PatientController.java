package com.example.Hospital.controller;

import com.example.Hospital.dto.PatientDto;
import com.example.Hospital.dto.request.CreatePatientRequest;
import com.example.Hospital.dto.request.UpdatePatientRequest;
import com.example.Hospital.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody CreatePatientRequest createPatientRequest){
        return ResponseEntity.ok().body(patientService.createPatient(createPatientRequest));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(patientService.getPatientById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") String id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") String id,
                                                    @RequestBody UpdatePatientRequest updatePatientRequest){
        return ResponseEntity.ok().body(patientService.updatePatient(id, updatePatientRequest));
    }
}
