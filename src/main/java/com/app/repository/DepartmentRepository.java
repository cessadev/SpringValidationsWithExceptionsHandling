package com.app.repository;

import com.app.entities.Department;
import com.app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT u FROM Department u WHERE u.name = :name")
    List<Department> findByName(@Param("name") String name);
}
