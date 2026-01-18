package com.fiap.sus_triage.strategy;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.enums.RiskLevel;

public interface RiskLevelStrategy {

    RiskLevel classify(TriageRequestDTO data);
    
}
