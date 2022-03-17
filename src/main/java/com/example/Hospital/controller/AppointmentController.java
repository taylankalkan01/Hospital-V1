package com.example.Hospital.controller;

import com.example.Hospital.dto.AppointmentDto;
import com.example.Hospital.dto.request.CreateAppointmentRequest;
import com.example.Hospital.dto.request.UpdateAppointmentRequest;
import com.example.Hospital.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest){
        return ResponseEntity.ok().body(appointmentService.createAppointment(createAppointmentRequest));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(){
        return ResponseEntity.ok().body(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentByDoctorId(@PathVariable("id") String doctorId){
        return ResponseEntity.ok().body(appointmentService.getAppointmentByDoctorId(doctorId));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentByPatientId(@PathVariable("id") String patientId){
        return ResponseEntity.ok().body(appointmentService.getAppointmentByPatientId(patientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable("id") Integer id, @RequestBody UpdateAppointmentRequest updateAppointmentRequest){
        return ResponseEntity.ok().body(appointmentService.updateAppointment(id, updateAppointmentRequest));
    }










}
