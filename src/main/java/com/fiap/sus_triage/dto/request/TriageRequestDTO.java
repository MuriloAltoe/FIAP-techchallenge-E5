package com.fiap.sus_triage.dto.request;

import lombok.Data;

@Data
public class TriageRequestDTO {
    
    private Long patientId;
    private String problem;
    private Integer painIntensity;
    private Boolean shortnessOfBreathe;
}
