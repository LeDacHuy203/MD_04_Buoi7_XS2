package com.medical.patient.config;

import com.medical.patient.entity.Patient;
import com.medical.patient.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(PatientRepository patientRepository) {
        return args -> {
            if (patientRepository.count() == 0) {
                patientRepository.save(new Patient(
                        null,
                        "Tran Van Mau",
                        "1985-10-20",
                        "Nam",
                        "0909123456",
                        "456 Tran Phu, Quan 5, TP. HCM",
                        "Tien su benh: Binh thuong"
                ));
                patientRepository.save(new Patient(
                        null,
                        "Le Thi Hoa",
                        "1995-03-12",
                        "Nu",
                        "0988765432",
                        "789 Nguyen Trai, Ha Noi",
                        "Tien su: Di ung thuoc khang sinh"
                ));
                System.out.println(">>> [Patient Service] Da khoi tao 2 benh nhan mau trong PostgreSQL!");
            }
        };
    }
}
