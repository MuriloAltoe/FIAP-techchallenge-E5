package com.fiap.sus_triage.strategy;

import org.springframework.stereotype.Component;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.entity.RiskClassification;

@Component
public class ManchesterStrategy implements RiskClassificationStrategy {

    @Override
    public RiskClassification classify(TriageRequestDTO data) {
        if (Boolean.TRUE.equals(data.getShortnessOfBreathe())) {
            return RiskClassification.RED;
        }

        if (data.getPainIntensity() != null && data.getPainIntensity() >= 8) {
            return RiskClassification.ORANGE;
        }

        return RiskClassification.GREEN;
    }
}
