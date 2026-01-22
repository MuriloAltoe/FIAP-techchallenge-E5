package com.fiap.sus_triage.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.sus_triage.dto.request.PatientRequestDTO;
import com.fiap.sus_triage.dto.response.PatientResponseDTO;
import com.fiap.sus_triage.entity.Patient;
import com.fiap.sus_triage.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO dto) {

        Patient patient = new Patient(
                null,
                dto.getName(),
                dto.getAge(),
                dto.getGender());

        patientRepository.save(patient);

        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender());

    }

    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return new PatientResponseDTO(patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender());
    }

    public List<PatientResponseDTO> getAllPatients(int page, int size) {
        return patientRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size))
                .stream()
                .map(patient -> new PatientResponseDTO(
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getGender()))
                .toList();
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found"));

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());

        patientRepository.save(patient);

        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender());
    }

    public void deletePatient(Long id) {

        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient not found");
        }

        patientRepository.deleteById(id);
    }

}
