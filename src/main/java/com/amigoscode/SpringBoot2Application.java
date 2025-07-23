package com.amigoscode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@RestController
public class SpringBoot2Application implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO worker (department, fullname, role) VALUES (?, ?, ?)";

        int result = jdbcTemplate.update(sql, "Java", "Ayo Bemidebe", "DevOps");

        if (result > 0) {
            System.out.println("Worker inserted successfully");
        }

    }
}
