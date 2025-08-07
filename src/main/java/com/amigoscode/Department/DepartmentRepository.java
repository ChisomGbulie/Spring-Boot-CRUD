package com.amigoscode.Department;

import com.amigoscode.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    //<Table name, P-K>
    Optional<Role> findByDepartment(String department);
}
