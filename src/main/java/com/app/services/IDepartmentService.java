package com.app.services;

import com.app.entities.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    Optional<Department> findById(Long id);
    List<Department> findByName(String name);
}
