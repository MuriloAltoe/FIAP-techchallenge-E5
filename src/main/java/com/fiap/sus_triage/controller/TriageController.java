package com.fiap.sus_triage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.sus_triage.controller.swagger.TriageControllerSwaggerConfiguration;
import com.fiap.sus_triage.dto.TriageQueueDTO;
import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;
import com.fiap.sus_triage.service.TriageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/triagem")
@RequiredArgsConstructor
public class TriageController implements TriageControllerSwaggerConfiguration {

    private final TriageService triageService;

    @PostMapping
    public ResponseEntity<TriageResponseDTO> createTriage(
            @RequestBody TriageRequestDTO dto) {
        return ResponseEntity.ok(triageService.createTriage(dto));
    }

    @GetMapping("/fila")
    public ResponseEntity<List<TriageQueueDTO>> getQueue() {
        return ResponseEntity.ok(triageService.getPrioritizedQueue());
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startTriage(@PathVariable Long id) {
        triageService.startTriage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<Void> finishTriage(@PathVariable Long id) {
        triageService.finishTriage(id);
        return ResponseEntity.noContent().build();
    }
}
