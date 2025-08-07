package com.amigoscode.Worker;

import com.amigoscode.Department.Department;
import com.amigoscode.Department.DepartmentRepository;
import com.amigoscode.Role.Role;
import com.amigoscode.Role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
public class WorkerController {

    private final WorkerService workerService;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public WorkerController(WorkerService workerService,
                            WorkerRepository workerRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository) {
        this.workerService = workerService;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    // Read or  GET Operation for All Workers
    @GetMapping
    public List<Worker> getWorkers() {
        return workerService.getAllWorkers();
    }

    // Read / GET Operation by ID
    @GetMapping("{id}")
    public Worker getWorkerById(
            @PathVariable Integer id
    ) {
        return workerService.getWorkerById(id);
    }

    // Read / GET Operation by departmentId
    @GetMapping("/department/{departmentId}")
    public List<Worker> getWorkersByDepartmentId(
            @PathVariable Integer departmentId
    ) {
        return workerService.getWorkersByDepartmentId(departmentId);
    }

    // Read / GET Operation by roleId
    @GetMapping("/role/{roleId}")
    public List<Worker> getWorkersByRoleId(
            @PathVariable Integer roleId
    ) {
        return workerService.getWorkersByRoleId(roleId);
    }

    // Create / POST Operation
    @PostMapping
    public ResponseEntity<?> addNewWorker(
            @RequestBody WorkerDTO workerDTO)
    {
        try {
            // Verify department exists
            Department department = departmentRepository.findById(workerDTO.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));

            // Verify role exists
            Role role = roleRepository.findById(workerDTO.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));

            // Create and save worker
            Worker worker = new Worker();
            worker.setFullname(workerDTO.getFullname());
            worker.setDepartment(department);
            worker.setRole(role);

            Worker savedWorker = workerService.insertWorker(worker);
            return ResponseEntity.ok(worker);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating worker: " + e.getMessage());
        }
    }

    // Update PUT Operation
    @PutMapping("{id}")
    @Transactional
    public Worker updateWorker(
            @PathVariable Integer id,
            @RequestParam(required = false) String fullname,
            @RequestParam(required = false, name = "department_id") Integer departmentId,
            @RequestParam(required = false, name = "role_id") Integer roleId
    ) {
        workerService.updateWorker(
                id,
                fullname,
                departmentId != null ? departmentId.toString() : null,
                roleId != null ? roleId.toString() : null);
        return workerService.getWorkerById(id);
    }

    @DeleteMapping("{id}")
    public void deleteWorker(@PathVariable Integer id) {
        workerService.deleteWorker(id);
    }
}


