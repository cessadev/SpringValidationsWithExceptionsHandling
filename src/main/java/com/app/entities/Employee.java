package com.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    private Integer age;

    @Column(name = "performance_evaluation")
    private double performanceEvaluation;

    private boolean married;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(targetEntity = Department.class, cascade = CascadeType.PERSIST)
    private Department department;
}
