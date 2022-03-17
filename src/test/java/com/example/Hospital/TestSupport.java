package com.example.Hospital;

import com.example.Hospital.dto.AppointmentDto;
import com.example.Hospital.dto.DoctorDto;
import com.example.Hospital.dto.PatientDto;
import com.example.Hospital.dto.request.*;
import com.example.Hospital.model.*;

import java.util.List;

import static com.example.Hospital.model.Department.RADIOLOGISTS;
import static com.example.Hospital.model.NamePrefix.PHD;

public class TestSupport {

    //Doctor
    public CreateDoctorRequest generateCreateDoctorRequest(){
        CreateDoctorRequest createDoctorRequest = new CreateDoctorRequest();
        createDoctorRequest.setId("id");
        createDoctorRequest.setName("name");
        createDoctorRequest.setNamePrefix(PHD);
        createDoctorRequest.setDepartment(RADIOLOGISTS);
        createDoctorRequest.setDateOfStart(2018);
        createDoctorRequest.setDateOfGraduate(2022);
        return createDoctorRequest;
    }

    public DoctorDto generateDoctorDto() {
        return DoctorDto.builder()
                .id("id")
                .name("name")
                .namePrefix(PHD)
                .department(RADIOLOGISTS)
                .dateOfStart(2018)
                .dateOfGraduate(2022)
                .build();
    }

    public Doctor generateDoctor(){
        return Doctor.builder()
                .id("id")
                .name("name")
                .namePrefix(PHD)
                .department(RADIOLOGISTS)
                .dateOfStart(2018)
                .dateOfGraduate(2022)
                .build();
    }

    public List<Doctor> generateDoctorList(){
        Doctor doctor = generateDoctor();
        return List.of(doctor);
    }

    public List<DoctorDto> generateDoctorDtoList(){
        DoctorDto doctorDto = generateDoctorDto();
        return List.of(doctorDto);
    }

    public UpdateDoctorRequest generateUpdateDoctorRequest(){
        UpdateDoctorRequest updateDoctorRequest = new UpdateDoctorRequest();
        updateDoctorRequest.setName("name");
        updateDoctorRequest.setNamePrefix(PHD);
        updateDoctorRequest.setDepartment(RADIOLOGISTS);
        updateDoctorRequest.setDateOfStart(2018);
        updateDoctorRequest.setDateOfGraduate(2022);
        return updateDoctorRequest;
    }




    //Appointment
    public CreateAppointmentRequest generateCreateAppointmentRequest(){
        CreateAppointmentRequest createAppointmentRequest = new CreateAppointmentRequest();
        createAppointmentRequest.setId(1);
        createAppointmentRequest.setPatientId("pId");
        createAppointmentRequest.setDoctorId("dId");
        createAppointmentRequest.setDate("date");
        createAppointmentRequest.setHour(1);
        createAppointmentRequest.setMinute(1);
        createAppointmentRequest.setNotes("note");
        return createAppointmentRequest;

    }

    public AppointmentDto generateAppointmentDto() {
        return AppointmentDto.builder()
                .id(1)
                .patientId("pId")
                .doctorId("dId")
                .date("date")
                .hour(1)
                .minute(1)
                .notes("note")
                .build();

    }

    public Appointment generateAppointment(){
        return Appointment.builder()
                .id(1)
                .patientId("pId")
                .doctorId("dId")
                .date("date")
                .hour(1)
                .minute(1)
                .notes("note")
                .build();
    }

    public List<Appointment> generateAppointmentList(){

        Appointment appointment = generateAppointment();
        return List.of(appointment);
    }

    public List<AppointmentDto> generateAppointmentDtoList(){
        AppointmentDto appointmentDto = generateAppointmentDto();
        return List.of(appointmentDto);
    }

    public UpdateAppointmentRequest generateUpdateAppointmentRequest(){
        UpdateAppointmentRequest updateAppointmentRequest = new UpdateAppointmentRequest();
        updateAppointmentRequest.setDoctorId("dId");
        updateAppointmentRequest.setDate("date");
        updateAppointmentRequest.setHour(1);
        updateAppointmentRequest.setMinute(1);
        updateAppointmentRequest.setNotes("note");
        return updateAppointmentRequest;
    }


//patient
public CreatePatientRequest generateCreatePatientRequest(){
    CreatePatientRequest createPatientRequest = new CreatePatientRequest();
    createPatientRequest.setId("id");
    createPatientRequest.setName("name");
    createPatientRequest.setAddress("address");
    createPatientRequest.setCity(City.ISTANBUL);
    createPatientRequest.setGender(Gender.MALE);
    createPatientRequest.setHealthInsurance(true);
    createPatientRequest.setDateOfBirth(2001);

    return createPatientRequest;

}

    public PatientDto generatePatientDto() {
        return PatientDto.builder()
                .id("id")
                .name("name")
                .address("address")
                .city(City.ISTANBUL)
                .gender(Gender.MALE)
                .healthInsurance(true)
                .dateOfBirth(2001)
                .build();

    }

    public Patient generatePatient(){
        return Patient.builder()
                .id("id")
                .name("name")
                .address("address")
                .city(City.ISTANBUL)
                .gender(Gender.MALE)
                .healthInsurance(true)
                .dateOfBirth(2001)
                .build();

    }

    public List<Patient> generatePatientList(){

        Patient patient = generatePatient();
        return List.of(patient);
    }

    public List<PatientDto> generatePatientDtoList(){
        PatientDto patientDto = generatePatientDto();
        return List.of(patientDto);
    }

    public UpdatePatientRequest generateUpdatePatientRequest(){
        UpdatePatientRequest updatePatientRequest = new UpdatePatientRequest();
        updatePatientRequest.setName("name");
        updatePatientRequest.setAddress("address");
        updatePatientRequest.setCity(City.ISTANBUL);
        updatePatientRequest.setGender(Gender.MALE);
        updatePatientRequest.setHealthInsurance(true);
        updatePatientRequest.setDateOfBirth(2001);
        return updatePatientRequest;
    }


}
