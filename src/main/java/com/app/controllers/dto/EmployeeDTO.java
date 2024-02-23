package com.app.controllers.dto;

import com.app.advice.validation.anotation.ValidName;
import com.app.advice.validation.anotation.ValidPhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;
    
    @ValidName
    private String name;

    @ValidName
    private String lastName;

    @NotNull
    @Email
    private String email;

    @ValidPhoneNumber
    private Long phoneNumber;

    @NotNull
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private Integer age;

    @NotNull
    @Digits(integer = 1, fraction = 1)
    private double performanceEvaluation;

    @NotNull
    private boolean married;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @Valid
    private DepartmentDTO departmentDTO;
}
