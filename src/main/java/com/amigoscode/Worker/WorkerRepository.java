package com.amigoscode.Worker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    List<Worker> findByDepartmentId(Integer departmentId);
    List<Worker> findByRoleId(Integer roleId);

    //<Table name, P-K>
}
