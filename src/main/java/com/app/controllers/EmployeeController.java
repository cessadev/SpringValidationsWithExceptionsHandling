package com.app.controllers;

import com.app.advice.CustomException.ResourceNotFoundException;
import com.app.advice.CustomException.CustomDataAccessException;
import com.app.controllers.dto.DepartmentDTO;
import com.app.controllers.dto.EmployeeDTO;
import com.app.entities.Department;
import com.app.entities.Employee;
import com.app.services.IDepartmentService;
import com.app.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);

        try {
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();

                EmployeeDTO employeeDTO = EmployeeDTO.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .phoneNumber(employee.getPhoneNumber())
                        .age(employee.getAge())
                        .performanceEvaluation(employee.getPerformanceEvaluation())
                        .married(employee.isMarried())
                        .dateOfBirth(employee.getDateOfBirth())
                        .departmentDTO(DepartmentDTO.builder()
                                .name(employee.getDepartment().getName())
                                .build())
                        .build();

                return ResponseEntity.ok(employeeDTO);
            } else {
                throw new ResourceNotFoundException("El recurso con ID " + id + " no se encontró.");
            }
        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        try {
            List<EmployeeDTO> employeesDTOList = employeeService.findAll()
                    .stream()
                    .map(employee -> EmployeeDTO.builder()
                            .id(employee.getId())
                            .name(employee.getName())
                            .lastName(employee.getLastName())
                            .email(employee.getEmail())
                            .phoneNumber(employee.getPhoneNumber())
                            .age(employee.getAge())
                            .performanceEvaluation(employee.getPerformanceEvaluation())
                            .married(employee.isMarried())
                            .dateOfBirth(employee.getDateOfBirth())
                            .departmentDTO(DepartmentDTO.builder()
                                    .name(employee.getDepartment().getName())
                                    .build())
                            .build())
                    .toList();

            if (employeesDTOList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(employeesDTOList);

        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByName(name);

        try {
            if (employees.isEmpty()) {
                return ResponseEntity.notFound().build();

            } else {
                List<EmployeeDTO> employeeDTOS = new ArrayList<>();
                for (Employee employee : employees) {
                    EmployeeDTO employeeDTO = EmployeeDTO.builder()
                            .id(employee.getId())
                            .name(employee.getName())
                            .lastName(employee.getLastName())
                            .email(employee.getEmail())
                            .phoneNumber(employee.getPhoneNumber())
                            .age(employee.getAge())
                            .performanceEvaluation(employee.getPerformanceEvaluation())
                            .married(employee.isMarried())
                            .dateOfBirth(employee.getDateOfBirth())
                            .departmentDTO(DepartmentDTO.builder()
                                    .name(employee.getDepartment().getName())
                                    .build())
                            .build();
                    employeeDTOS.add(employeeDTO);
                }

                return ResponseEntity.ok(employeeDTOS);
            }
        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> save(@RequestBody @Valid EmployeeDTO employeeDTO) throws URISyntaxException {
        List<Department> departments = departmentService.findByName(employeeDTO.getDepartmentDTO().getName());

        try {
            if (departments.isEmpty()) {
                throw new ResourceNotFoundException("El departamento indicado no se encuentra registrado");
            } else {
                Department department = departments.get(0);
                employeeService.save(Employee.builder()
                        .name(employeeDTO.getName())
                        .lastName(employeeDTO.getLastName())
                        .email(employeeDTO.getEmail())
                        .phoneNumber(employeeDTO.getPhoneNumber())
                        .age(employeeDTO.getAge())
                        .performanceEvaluation(employeeDTO.getPerformanceEvaluation())
                        .married(employeeDTO.isMarried())
                        .dateOfBirth(employeeDTO.getDateOfBirth())
                        .department(department)
                        .build());

                return ResponseEntity.created(new URI("/api/employee/save")).build();
            }

        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Map<String, Object> updates) {
        Optional<Employee> employeeOptional = employeeService.findById(id);

        try {
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();

                if (updates.containsKey("name")) {
                    employee.setName((String) updates.get("name"));
                }
                if (updates.containsKey("lastName")) {
                    employee.setLastName((String) updates.get("lastName"));
                }
                if (updates.containsKey("email")) {
                    employee.setEmail((String) updates.get("email"));
                }
                if (updates.containsKey("phoneNumber")) {
                    employee.setPhoneNumber(Long.parseLong(String.valueOf(updates.get("phoneNumber"))));
                }
                if (updates.containsKey("age")) {
                    employee.setAge(Integer.parseInt(String.valueOf(updates.get("age"))));
                }
                if (updates.containsKey("performanceEvaluation")) {
                    employee.setPerformanceEvaluation((double) updates.get("performanceEvaluation"));
                }
                if (updates.containsKey("married")) {
                    employee.setMarried((boolean) updates.get("married"));
                }
                if (updates.containsKey("dateOfBirth")) {
                    String dateOfBirthStr = (String) updates.get("dateOfBirth");
                    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
                    employee.setDateOfBirth(dateOfBirth);
                }
                if (updates.containsKey("departmentDTO") && updates.get("departmentDTO") instanceof Map) {
                    Map<String, Object> departmentMap = (Map<String, Object>) updates.get("departmentDTO");
                    Department department = Department.builder()
                            .name((String) departmentMap.get("name"))
                            .build();
                    employee.setDepartment(department);
                }

                employeeService.save(employee);
                return ResponseEntity.ok("Updated successfully");

            } else {
                throw new ResourceNotFoundException("El recurso con ID " + id + " no se encontró.");
            }
        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        try {
            if (optionalEmployee.isPresent()) {
                employeeService.deleteById(id);
                return ResponseEntity.ok("Deleted successfully");
            } else {
                throw new ResourceNotFoundException("El recurso con ID " + id + " no se encontró.");
            }
        } catch (DataAccessException exception) {
            throw new CustomDataAccessException("Error al acceder a la base de datos: ", exception);
        }
    }

}


