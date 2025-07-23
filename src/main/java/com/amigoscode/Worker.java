package com.amigoscode;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Auto generated ID values

    private Integer id;
    private String Fullname;
    private String Department;
    private String Role;

    public Worker() {
    }

    public Worker(Integer id, String fullname, String department, String role) {
        this.id = id;
        Fullname = fullname;
        Department = department;
        Role = role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(Fullname, worker.Fullname) && Objects.equals(Department, worker.Department) && Objects.equals(Role, worker.Role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Fullname, Department, Role);
    }
}
