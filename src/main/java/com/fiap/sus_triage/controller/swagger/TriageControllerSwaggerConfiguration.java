package com.fiap.sus_triage.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.fiap.sus_triage.dto.TriageQueueDTO;
import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Triagens", description = "Gerenciamento de triagens")
public interface TriageControllerSwaggerConfiguration {

    @Operation(summary = "Criar uma nova triagem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Triagem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @RequestBody(
        description = "Dados para criação da triagem (exemplo gera risco AMARELO)",
        required = true,
        content = @Content(
            examples = @ExampleObject(
                value = """
                {
                  "patientId": 1,
                  "problem": "Dor abdominal persistente",
                  "painIntensity": 6,
                  "shortnessOfBreath": false,
                  "chestPain": false,
                  "lossOfConsciousness": false,
                  "activeBleeding": false,
                  "seizures": false,
                  "heartRate": 95,
                  "systolicPressure": 120,
                  "oxygenSaturation": 96,
                  "bodyTemperature": 38.2,
                  "vomiting": false,
                  "fever": true
                }
                """
            )
        )
    )
    ResponseEntity<TriageResponseDTO> createTriage(TriageRequestDTO dto);

    @Operation(summary = "Obter fila de triagens priorizadas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fila de triagens retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<List<TriageQueueDTO>> getQueue();

    @Operation(summary = "Iniciar uma triagem")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Triagem iniciada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Triagem não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<Void> startTriage(@PathVariable Long id);

    @Operation(summary = "Finalizar uma triagem")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Triagem finalizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Triagem não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<Void> finishTriage(@PathVariable Long id);
}
