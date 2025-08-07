package com.amigoscode;

import com.amigoscode.Department.Department;
import com.amigoscode.Department.DepartmentRepository;
import com.amigoscode.Role.Role;
import com.amigoscode.Role.RoleRepository;
import com.amigoscode.Worker.Worker;
import com.amigoscode.Worker.WorkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringBoot2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(WorkerRepository workerRepository,
                                  DepartmentRepository departmentRepository,
                                  RoleRepository roleRepository) {
        return args -> {
            // Initialize sample data
            Department department = new Department("Java");
            department = departmentRepository.save(department);

            Role role = new Role("DevOps");
            role = roleRepository.save(role);

            Worker worker = new Worker();
            worker.setFullname("Ayo Bemidebe");
            worker.setDepartment(department);
            worker.setRole(role);
            workerRepository.save(worker);
        };
    }
}