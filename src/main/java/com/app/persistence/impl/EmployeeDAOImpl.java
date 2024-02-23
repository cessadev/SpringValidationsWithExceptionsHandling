package com.app.persistence.impl;

import com.app.entities.Employee;
import com.app.persistence.IEmployeeDAO;
import com.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDAOImpl implements IEmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() { return (List<Employee>) employeeRepository.findAll(); }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) { employeeRepository.save(employee); }

    @Override
    public void deleteById(Long id) { employeeRepository.deleteById(id); }

    @Override
    public List<Employee> findByName(String name) { return (List<Employee>) employeeRepository.findByName(name); }
}
