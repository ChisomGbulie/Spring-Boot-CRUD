package com.amigoscode;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/worker")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerRepository workerRepository;

    public WorkerController(WorkerService workerService,
                            WorkerRepository workerRepository) {
        this.workerService = workerService;
        this.workerRepository = workerRepository;
    }

    // Read / GET Operation for All
    @GetMapping
    public List<Worker> getWorkers() {
        return workerService.getAllWorkers();
    }

    // Read / GET Operation by Id
    @GetMapping("{id}")
    public Worker getWorkerById(
            @PathVariable Integer id
    ) {
        return workerService.getWorkerById(id);
    }

    // Create / POST Operation
    @PostMapping
    public void addNewWorker(
            @RequestBody Worker worker
            //@Request Body maps a Post Request JSON body to this
            //java class for smooth operation
    ) {
        workerService.insertWorker(worker);
    }

    // Update PUT Operation
    @PutMapping("{id}")
    @Transactional
    public Worker updateWorker(
            @PathVariable Integer id,
            @RequestParam(required = false) String fullname,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String role) {
        workerService.updateWorker(id, fullname, department, role);
        return null;
    }

    @DeleteMapping("{id}")
    public void deleteWorker(@PathVariable Integer id) {
        workerService.deleteWorker(id);
    }
}


