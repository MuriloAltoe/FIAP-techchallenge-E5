package com.fiap.sus_triage.dto;

import java.time.LocalDateTime;

import com.fiap.sus_triage.enums.RiskLevel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriageQueueDTO {

    private Long triageId;
    private String patientName;
    private RiskLevel riskLevel;
    private LocalDateTime waitingSince;
}
