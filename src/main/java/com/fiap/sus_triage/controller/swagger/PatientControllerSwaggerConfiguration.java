package com.fiap.sus_triage.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fiap.sus_triage.dto.request.PatientRequestDTO;
import com.fiap.sus_triage.dto.response.PatientResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
public interface PatientControllerSwaggerConfiguration {

    @Operation(summary = "Criar um novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO dto);

    @Operation(summary = "Buscar paciente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id);

    @Operation(summary = "Listar todos os pacientes com paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size);

    @Operation(summary = "Atualizar um paciente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientRequestDTO dto);

    @Operation(summary = "Deletar um paciente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id);

}
