package com.fiap.sus_triage.strategy;

import org.springframework.stereotype.Component;

import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.enums.RiskLevel;

@Component
public class ManchesterStrategy implements RiskLevelStrategy {

    @Override
    public RiskLevel classify(TriageRequestDTO data) {

        if (Boolean.TRUE.equals(data.getLossOfConsciousness())
                || Boolean.TRUE.equals(data.getSeizures())
                || Boolean.TRUE.equals(data.getActiveBleeding())
                || Boolean.TRUE.equals(data.getChestPain())
                || Boolean.TRUE.equals(data.getShortnessOfBreath())
                || isOxygenCritical(data)
                || isHemodynamicInstability(data)) {

            return RiskLevel.RED;
        }

        if (isSeverePain(data)
                || isHighFever(data)
                || isTachycardia(data)
                || isHypotension(data)) {

            return RiskLevel.ORANGE;
        }

        if (isModeratePain(data)
                || Boolean.TRUE.equals(data.getVomiting())
                || Boolean.TRUE.equals(data.getFever())) {

            return RiskLevel.YELLOW;
        }

        if (data.getPainIntensity() != null && data.getPainIntensity() == 0) {
            return RiskLevel.BLUE;
        }

        return RiskLevel.GREEN;
    }

    private boolean isSeverePain(TriageRequestDTO data) {
        return data.getPainIntensity() != null && data.getPainIntensity() >= 8;
    }

    private boolean isModeratePain(TriageRequestDTO data) {
        return data.getPainIntensity() != null
                && data.getPainIntensity() >= 4
                && data.getPainIntensity() <= 7;
    }

    private boolean isHighFever(TriageRequestDTO data) {
        return data.getBodyTemperature() != null && data.getBodyTemperature() >= 39.0;
    }

    private boolean isTachycardia(TriageRequestDTO data) {
        return data.getHeartRate() != null && data.getHeartRate() >= 130;
    }

    private boolean isHypotension(TriageRequestDTO data) {
        return data.getSystolicPressure() != null && data.getSystolicPressure() < 90;
    }

    private boolean isOxygenCritical(TriageRequestDTO data) {
        return data.getOxygenSaturation() != null && data.getOxygenSaturation() < 90;
    }

    private boolean isHemodynamicInstability(TriageRequestDTO data) {
        return data.getHeartRate() != null
                && data.getSystolicPressure() != null
                && data.getHeartRate() > 120
                && data.getSystolicPressure() < 90;
    }
}
