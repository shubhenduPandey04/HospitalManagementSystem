package com.hms.hospital_management_system.service;

import com.hms.hospital_management_system.models.Patient;
import com.hms.hospital_management_system.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    public static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size){
        try{
            System.out.println("Into Service Layer");
            Pageable pageable = PageRequest.of(page, size);
            return patientRepository.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message: "+ e.getMessage());
            logger.error("An error occurred while tracking patient details: {}",e.getMessage());
            return null;
        }
    }

    public Patient getPatientsById(Long id){
        try{
            Optional<Patient> patient = patientRepository.findById(id);
            return patient.orElse(null);
        }catch(Exception e){
            System.out.println("Error message: "+ e.getMessage());
            logger.error("An error occurred while tracking patient by Id {} : {}",id,e.getMessage());
            return null;
        }
    }

    public Patient createPatient(Patient patient){
        try{
            patientRepository.save(patient);
            return patient;
        }catch(Exception e){
            System.out.println("Error message: "+ e.getMessage());
            return null;
        }
    }

    public void deletePatient(Long id){
        try{
            logger.info("Deleting patient with id : {}", id);
            patientRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Error message: "+ e.getMessage());

        }
    }

    public Patient updatePatient(Long id, Patient updatePatient){
        try{
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if(existingPatient.isPresent()){
                Patient p = existingPatient.get();
                p.setName(updatePatient.getName());
                p.setAge(updatePatient.getAge());
                p.setGender(updatePatient.getGender());
                patientRepository.save(p);
                return updatePatient;
            }else {
                logger.error("Patient with ID {} not found", id);
                return null;
            }
        }catch(Exception e){
            System.out.println("Error message: "+ e.getMessage());
            return null;
        }
    }
}
