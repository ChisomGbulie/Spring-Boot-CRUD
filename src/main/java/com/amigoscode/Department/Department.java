package com.amigoscode.Department;

import com.amigoscode.Worker.Worker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Auto generated ID values
    private Integer id;

    @Column(name = "department", unique = true, nullable = false, length = 100)
    private String department;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("department")
    private List<Worker> workers;


    public Department() {
    }

    public Department(String department) {
        this.department = department;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
    this.id = id;
    }


    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }


    public List<Worker> getWorkers() {
        return workers;
    }
    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(department, that.department) && Objects.equals(workers, that.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, workers);
    }
}

