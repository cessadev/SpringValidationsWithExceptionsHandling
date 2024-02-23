package com.app.repository;

import com.app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT u FROM Employee u WHERE u.name = :name")
    List<Employee> findByName(@Param("name") String name);
}
