package com.app.services.impl;

import com.app.entities.Employee;
import com.app.persistence.IEmployeeDAO;
import com.app.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public List<Employee> findAll() { return employeeDAO.findAll(); }
    @Override
    public Optional<Employee> findById(Long id) { return employeeDAO.findById(id); }
    @Override
    public void save(Employee employee) { employeeDAO.save(employee); }
    @Override
    public void deleteById(Long id) { employeeDAO.deleteById(id); }
    @Override
    public List<Employee> findByName(String name) { return employeeDAO.findByName(name); }
}
