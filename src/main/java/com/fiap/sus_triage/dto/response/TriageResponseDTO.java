package com.fiap.sus_triage.dto.response;

import com.fiap.sus_triage.entity.RiskClassification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriageResponseDTO {
    
    private Long triageId;
    private String patientName;
    private RiskClassification riskClassification;
    private String priority;
}
