package com.app.controllers.dto;

import com.app.advice.validation.anotation.ValidName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {
    @ValidName
    private String name;
}
