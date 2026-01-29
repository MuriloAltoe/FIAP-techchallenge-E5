package com.fiap.sus_triage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.sus_triage.controller.swagger.PatientControllerSwaggerConfiguration;
import com.fiap.sus_triage.dto.request.PatientRequestDTO;
import com.fiap.sus_triage.dto.response.PatientResponseDTO;
import com.fiap.sus_triage.service.PatientService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PatientController implements PatientControllerSwaggerConfiguration {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @RequestBody PatientRequestDTO dto) {

        return ResponseEntity.ok(patientService.createPatient(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(
            @PathVariable Long id) {

        return ResponseEntity.ok(patientService.getPatientById(id));

    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(patientService.getAllPatients(page, size));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientRequestDTO dto) {

        return ResponseEntity.ok(patientService.updatePatient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
