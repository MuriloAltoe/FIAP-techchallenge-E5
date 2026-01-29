package com.fiap.sus_triage.controller.swagger;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.fiap.sus_triage.dto.TriageQueueDTO;
import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Triagens", description = "Gerenciamento de triagens")
public interface TriageControllerSwaggerConfiguration {

    public ResponseEntity<TriageResponseDTO> createTriage(@RequestBody TriageRequestDTO dto);

    public ResponseEntity<List<TriageQueueDTO>> getQueue();

    public ResponseEntity<Void> startTriage(@PathVariable Long id);

    public ResponseEntity<Void> finishTriage(@PathVariable Long id);
}
