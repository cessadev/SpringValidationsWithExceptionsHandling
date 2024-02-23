package com.app.persistence;

import com.app.entities.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentDAO {
    Optional<Department> findById(Long id);
    List<Department> findByName(String name);
}
