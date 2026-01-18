package com.fiap.sus_triage.dto.response;

import com.fiap.sus_triage.enums.RiskLevel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class TriageResponseDTO {
    
    private Long triageId;
    private String patientName;
    private RiskLevel riskLevel;
    private String priority;
}
