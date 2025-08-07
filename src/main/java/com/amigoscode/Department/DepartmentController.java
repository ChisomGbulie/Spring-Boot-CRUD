package com.amigoscode.Department;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //Read or GET Operation for all Departments
    @GetMapping
    public List<Department> getDepartment() {
        return departmentService.getAllAvailableDepartments();
    }



    // Insert a unique Department (U.Q)
    @PostMapping
    public ResponseEntity<String> addNewDepartment(
            @RequestBody Department department
    ) {
        try {
            if (department.getDepartment() == null || department.getDepartment().isEmpty()) {
                return ResponseEntity.badRequest().body("Department name must be provided");
            }
            if (department.getWorkers() == null) {
                department.setWorkers(new ArrayList<>());
            }

            departmentService.insertDepartment(department);
            return ResponseEntity.ok("Department created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating department: " + e.getMessage());
        }
    }
}