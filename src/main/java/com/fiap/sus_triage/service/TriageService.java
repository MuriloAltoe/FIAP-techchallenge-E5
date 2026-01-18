package com.fiap.sus_triage.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.sus_triage.dto.TriageQueueDTO;
import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;
import com.fiap.sus_triage.entity.Patient;
import com.fiap.sus_triage.enums.RiskLevel;
import com.fiap.sus_triage.entity.Triage;
import com.fiap.sus_triage.enums.TriageStatus;
import com.fiap.sus_triage.repository.PatientRepository;
import com.fiap.sus_triage.repository.TriageRepository;
import com.fiap.sus_triage.strategy.RiskLevelStrategy;

@Service
public class TriageService {

    private final PatientRepository patientRepository;
    private final TriageRepository triageRepository;
    private final RiskLevelStrategy riskLevelStrategy;

    public TriageService(PatientRepository patientRepository, 
                         TriageRepository triageRepository, 
                         RiskLevelStrategy riskLevelStrategy
        ) {
        this.patientRepository = patientRepository;
        this.triageRepository = triageRepository;
        this.riskLevelStrategy = riskLevelStrategy;
    }

    public TriageResponseDTO createTriage(TriageRequestDTO dto){

        Patient patient = patientRepository.findById(dto.getPatientId())
                          .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        RiskLevel risk = riskLevelStrategy.classify(dto);

        Triage triage = new Triage(
            null,
            patient,
            dto.getProblem(),
            risk,
            TriageStatus.WAITING,
            LocalDateTime.now()
        );

        triageRepository.save(triage);

        return new TriageResponseDTO(
                triage.getId(),
                patient.getName(),
                risk,
                risk.name()
        );
    }

    public List<TriageQueueDTO> getPrioritizedQueue() {

        return triageRepository.findByStatus(TriageStatus.WAITING)
                .stream()
                .sorted(
                    Comparator
                        .comparingInt((Triage t) -> t.getRiskLevel().getPriority())
                        .thenComparing(Triage::getCreatedAt)
                )
                .map(triage -> new TriageQueueDTO(
                        triage.getId(),
                        triage.getPatient().getName(),
                        triage.getRiskLevel(),
                        triage.getCreatedAt()
                ))
                .toList();
    }
}
