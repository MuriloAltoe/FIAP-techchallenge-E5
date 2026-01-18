package com.fiap.sus_triage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.sus_triage.entity.Triage;

public interface TriageRepository extends JpaRepository<Triage, Long> {
    
}
