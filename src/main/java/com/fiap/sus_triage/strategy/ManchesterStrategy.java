package com.fiap.sus_triage.strategy;

import org.springframework.stereotype.Component;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.enums.RiskLevel;

@Component
public class ManchesterStrategy implements RiskLevelStrategy {

    @Override
    public RiskLevel classify(TriageRequestDTO data) {
        if (Boolean.TRUE.equals(data.getShortnessOfBreathe())) {
            return RiskLevel.RED;
        }

        if (data.getPainIntensity() != null && data.getPainIntensity() >= 8) {
            return RiskLevel.ORANGE;
        }

        return RiskLevel.GREEN;
    }
}
