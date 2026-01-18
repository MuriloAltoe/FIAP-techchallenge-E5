package com.fiap.sus_triage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.sus_triage.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
