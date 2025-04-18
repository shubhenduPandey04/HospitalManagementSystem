package com.hms.hospital_management_system.controllers;

import com.hms.hospital_management_system.models.Patient;
import com.hms.hospital_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public Page<Patient> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "2") int size) {
        System.out.println("Fetching the patients");
        return patientService.getAllPatients(page, size);
    }


    //@RequestBody ek annotation hai jo HTTP request ke body mein jo data aata hai
    // usko Java object mein convert karta hai.
    //Jo bhi JSON data aayega request body mein, use Patient class ke object mein convert kar do.
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        System.out.println("Creating patient");
        return patientService.createPatient(patient);
    }

    //@PathVariable use hota hai jab hum URL ke andar se dynamic values ko capture
    // karte hain aur unhe Java method ke parameters mein use karte hain.
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        System.out.println("Fetching patient with ID "+ id);
        return patientService.getPatientsById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        System.out.println("Deleting patient with ID: "+ id);
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id,@RequestBody Patient patient){
        System.out.println("Updating patient with ID: "+id);
        return patientService.updatePatient(id,patient);
    }
}
