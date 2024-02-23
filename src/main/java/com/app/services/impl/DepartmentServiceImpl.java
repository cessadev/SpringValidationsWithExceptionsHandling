package com.app.services.impl;

import com.app.entities.Department;
import com.app.persistence.IDepartmentDAO;
import com.app.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentDAO departmentDAO;
    @Override
    public Optional<Department> findById(Long id) { return departmentDAO.findById(id); }

    @Override
    public List<Department> findByName(String name) { return departmentDAO.findByName(name); }
}
