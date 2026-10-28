package com.medical.patient.service;

import com.medical.patient.entity.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient createPatient(Patient patient);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    void deletePatient(Long id);
}
