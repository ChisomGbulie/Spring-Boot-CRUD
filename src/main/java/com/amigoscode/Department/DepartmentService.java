package com.amigoscode.Department;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository
    ) {
        this.departmentRepository = departmentRepository;
    }

    //List Available or Empty Departments
    public List<Department> getAllAvailableDepartments() {
        return departmentRepository.findAll();
    }


    //Insert a unique Department (U.Q)
    public void insertDepartment(
            Department department
    ) {
        if (department.getDepartment() == null || department.getDepartment().isEmpty()) {
            throw new IllegalArgumentException("Department name must be provided");
        }
        if (departmentRepository.findByDepartment(department.getDepartment()).isPresent()) {
            throw new IllegalArgumentException("Department '" + department.getDepartment() + "' already exists");
        }
        departmentRepository.save(department);
    }
}


