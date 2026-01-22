package com.fiap.sus_triage.dto.response;

import com.fiap.sus_triage.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class PatientResponseDTO {
    
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
}
