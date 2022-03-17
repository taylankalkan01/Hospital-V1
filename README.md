# Hospital Project

___
The application has 3 apis
* DoctorAPI
* PatientAPI
* AppointmentAPI

---

1-) Doctor API ("v1/doctor")
-
```html
--Postman--

POST /v1/doctor - creates a new account for  Doctors
GET /v1/doctor/{id} - retrieve a Doctor by id
GET /v1/doctor - retrieves all Doctors
DELETE /v1/doctor/{id} - delete a Doctor by id
PUT /v1/doctor/{id} - update a Doctor by id
```

--CRUD OPERATIONS--

* createDoctor
* getAllDoctors
* getDoctorById
* deleteDoctor
* updateDoctor

Junit5 -> 100% methods, 100% lines covered.


Doctor MODEL
-

- id (String)
- name (String)
- namePrefix (Enum type)
- department (Enum type)
- dateOfGraduate (Integer)
- dateOfStart (Integer)

----

2-) Patient API ("v1/patient")
-
```html
--Postman--

POST /v1/patient - creates a new account for  patients
GET /v1/patient/{id} - retrieve a patient by id
GET /v1/patient - retrieves all patients
DELETE /v1/patient/{id} - delete a patient by id
PUT /v1/patient/{id} - update a patient by id
```
--CRUD OPERATIONS--

* createPatient
* getAllDPatient
* getPatientById
* deletePatient
* updatePatient

Junit5 -> 100% methods, 100% lines covered.


Patient MODEL
-

- id (String)
- name (String)
- gender (Enum type)
- dateOfBirth (Integer)
- city (Enum type)
- address (String)
- healthInsurance (boolean)


----


3-) Appointment API ("v1/appointment")
-
```html
--Postman--

POST /v1/appointment - creates a appointment for patients
GET /v1/appointment/{id} - retrieve a appointment by id
GET /v1/appointment/doctor/{id} - retrieve a appointment by Doctor id
GET /v1/appointment/patient/{id} - retrieve a appointment by Patient id
GET /v1/appointment - retrieves all appointments
DELETE /v1/appointment/{id} - delete a appointment by id
PUT /v1/appointment/{id} - update a appointment by id
```
--CRUD OPERATIONS--

* createAppointment
* getAllAppointments
* getAppointmentById
* getAppointmentByDoctorId
* getAppointmentByPatientId
* deleteAppointment
* updateAppointment

Junit5 -> 77% methods, 88% lines covered.


Patient MODEL
-

- id (String)
- docktorId (String)
- patientId (String)
- date (String)
- hour (Integer)
- minute (Integer)
- notes (String)



### Tech Stack

---
- Java 11
- Spring Boot
- Spring Data JPA and Hibernate
- Lombok
- H2 In memory database
- Docker 
- Docker compose 
- JUnit 5 
- Spring security (will be added)


### Prerequisites

---
- Maven
- Docker

---
### Run & Build

---
There are 2 ways of run & build the application.

#### Docker Compose

For docker compose usage, docker images already push to docker.io

You just need to run `docker-compose up` command
___
*$PORT: 8080*
```ssh
$ cd Hospital
$ docker-compose up
```

#### Maven

___
*$PORT: 8080*
```ssh
$ cd Hospital
$ mvn clean install
$ mvn spring-boot:run

```







