package com.fiap.sus_triage.entity;

import java.time.LocalDateTime;

import com.fiap.sus_triage.enums.RiskLevel;
import com.fiap.sus_triage.enums.TriageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "triage")
public class Triage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private String problem;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    @Enumerated(EnumType.STRING)
    private TriageStatus status;

    private LocalDateTime createdAt;
}
