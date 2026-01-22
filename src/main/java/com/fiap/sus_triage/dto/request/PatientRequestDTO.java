package com.fiap.sus_triage.dto.request;

import com.fiap.sus_triage.enums.Gender;

import lombok.Data;

@Data
public class PatientRequestDTO {

    private String name;
    private Integer age;
    private Gender gender;

}
