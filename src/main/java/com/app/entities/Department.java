package com.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employees;
}
