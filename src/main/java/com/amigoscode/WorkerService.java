package com.amigoscode;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public <List> WorkerService(
            WorkerRepository workerRepository
    ) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(
            Integer id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Worker with id " + id + " not found"));
    }

    public void insertWorker(
            Worker worker) {
        workerRepository.save(worker);
    }

    @Transactional
    public void updateWorker(Integer id,
                             String fullname,
                             String department,
                             String role
    ) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Worker with id " + id + " not found"
                ));
        if (fullname != null && fullname.length() > 0 && !Objects.equals(worker.getFullname(), fullname)) {
            worker.setFullname(fullname);
        }
        if (department != null && department.length() > 0 && !Objects.equals(worker.getDepartment(), department)) {
            worker.setDepartment(department);
        }
        if (role != null && role.length() > 0 && !Objects.equals(worker.getRole(), role)) {
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

