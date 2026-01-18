package com.fiap.sus_triage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.sus_triage.entity.Triage;
import com.fiap.sus_triage.enums.TriageStatus;

public interface TriageRepository extends JpaRepository<Triage, Long> {
    
    List<Triage> findByStatus(TriageStatus status);
}
