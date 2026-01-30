package com.fiap.sus_triage.dto.request;

import lombok.Data;

@Data
public class TriageRequestDTO {

    private Long patientId;

    /* Queixa principal */
    private String problem;

    /* Dor */
    private Integer painIntensity; // 0 a 10

    /* Sintomas críticos */
    private Boolean shortnessOfBreath;
    private Boolean chestPain;
    private Boolean lossOfConsciousness;
    private Boolean activeBleeding;
    private Boolean seizures;

    /* Sinais vitais */
    private Integer heartRate;       // bpm
    private Integer systolicPressure; // mmHg
    private Integer oxygenSaturation; // %
    private Double bodyTemperature;   // °C

    /* Outros */
    private Boolean vomiting;
    private Boolean fever;
}