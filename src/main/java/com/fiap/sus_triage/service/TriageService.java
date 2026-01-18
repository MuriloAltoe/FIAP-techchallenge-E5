package com.fiap.sus_triage.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;
import com.fiap.sus_triage.entity.Patient;
import com.fiap.sus_triage.entity.RiskClassification;
import com.fiap.sus_triage.entity.Triage;
import com.fiap.sus_triage.repository.PatientRepository;
import com.fiap.sus_triage.repository.TriageRepository;
import com.fiap.sus_triage.strategy.RiskClassificationStrategy;

@Service
public class TriageService {

    private final PatientRepository patientRepository;
    private final TriageRepository triageRepository;
    private final RiskClassificationStrategy riskClassificationStrategy;

    public TriageService(PatientRepository patientRepository, 
                         TriageRepository triageRepository, 
                         RiskClassificationStrategy riskClassificationStrategy
        ) {
        this.patientRepository = patientRepository;
        this.triageRepository = triageRepository;
        this.riskClassificationStrategy = riskClassificationStrategy;
    }

    public TriageResponseDTO realizeTriage(TriageRequestDTO dto){

        Patient patient = patientRepository.findById(dto.getPatientId())
        .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        RiskClassification risk = riskClassificationStrategy.classify(dto);

        Triage triage = new Triage(
            null,
            patient,
            dto.getProblem(),
            risk,
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
}
