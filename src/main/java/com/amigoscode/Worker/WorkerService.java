package com.amigoscode.Worker;

import com.amigoscode.Department.Department;
import com.amigoscode.Department.DepartmentRepository;
import com.amigoscode.Role.Role;
import com.amigoscode.Role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public WorkerService(
            WorkerRepository workerRepository,
            DepartmentRepository departmentRepository,
            RoleRepository roleRepository
    ) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Worker with id " + id + " not found"));
    }

    public List<Worker> getWorkersByDepartmentId(Integer departmentId) {
        return workerRepository.findByDepartmentId(departmentId);
    }

    public List<Worker> getWorkersByRoleId(Integer roleId) {
        return workerRepository.findByRoleId(roleId);
    }

    @Transactional
    public Worker insertWorker(Worker worker) {
        if (worker.getFullname() == null || worker.getFullname().isEmpty()) {
            throw new IllegalArgumentException("Full name must be provided");
        }
        if (worker.getDepartment() == null) {
            throw new IllegalArgumentException("Department must be provided");
        }
        if (worker.getRole() == null) {
            throw new IllegalArgumentException("Role must be provided");
        }
        return workerRepository.save(worker);
    }

    @Transactional
    public void updateWorker(Integer id,
                             String fullname,
                             String departmentId,
                             String roleId) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Worker with id " + id + " not found"
                ));

        if (fullname != null && !fullname.isEmpty() && !Objects.equals(worker.getFullname(), fullname)) {
            worker.setFullname(fullname);
        }

        if (departmentId != null && !departmentId.isEmpty()) {
            Department department = (Department) departmentRepository.findById(Integer.valueOf(departmentId))
                    .orElseThrow(() -> new IllegalStateException(
                            "Department with id " + departmentId + " not found"));
            worker.setDepartment(department);
        }

        if (roleId != null && !roleId.isEmpty()) {
            Role role = (Role) roleRepository.findById(Integer.valueOf(roleId))
                    .orElseThrow(() -> new IllegalStateException(
                            "Role with id " + roleId + " not found"));
            worker.setRole(role);
        }
    }

    public void deleteWorker(Integer id) {
        if (!workerRepository.existsById(id)) {
            throw new IllegalStateException("Worker with id " + id + " not found");
        }
        workerRepository.deleteById(id);
    }
}