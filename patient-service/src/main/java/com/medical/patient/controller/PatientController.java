package com.medical.patient.controller;

import com.medical.patient.entity.Patient;
import com.medical.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    // Config loaded dynamically from Config Server (Git Repo)
    @Value("${custom.message:Default fallback message}")
    private String customMessage;

    @Value("${spring.datasource.password:******}")
    private String dbPassword;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint tao moi benh nhan
     * POST http://localhost:8080/api/v1/patients (qua Gateway)
     * POST http://localhost:8081/api/v1/patients (truc tiep)
     */
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    /**
     * Endpoint lay danh sach benh nhan
     * GET http://localhost:8080/api/v1/patients
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    /**
     * Endpoint lay chi tiet benh nhan theo ID
     * GET http://localhost:8080/api/v1/patients/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint kiem tra cau hinh dong duoc load tu Git Config Server
     * Phuc vu cho Thu thach 4: Test Actuator /refresh ma khong can khoi dong lai
     * GET http://localhost:8080/api/v1/patients/config-check
     */
    @GetMapping("/config-check")
    public ResponseEntity<Map<String, Object>> getConfigCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("customMessage", customMessage);
        response.put("dbPassword", dbPassword);
        response.put("source", "Loaded from medical-config-repo via Config Server");
        response.put("status", "SUCCESS");
        return ResponseEntity.ok(response);
    }
}
