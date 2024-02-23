package com.app.persistence.impl;

import com.app.entities.Department;
import com.app.persistence.IDepartmentDAO;
import com.app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDAOImpl implements IDepartmentDAO {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findByName(String name) { return departmentRepository.findByName(name); }
}
