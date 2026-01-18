package com.fiap.sus_triage.strategy;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.entity.RiskClassification;

public interface RiskClassificationStrategy {

    RiskClassification classify(TriageRequestDTO data);
    
}
