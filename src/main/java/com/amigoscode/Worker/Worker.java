package com.amigoscode.Worker;


import com.amigoscode.Department.Department;
import com.amigoscode.Role.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Auto generated ID values

    private Integer id;

    @Column(length = 100)
    private String fullname;


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_worker_department",
                    foreignKeyDefinition = "FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE SET NULL ON UPDATE CASCADE"))
    @JsonIgnoreProperties("workers")
    private Department department;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_worker_role",
                    foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE SET NULL ON UPDATE CASCADE"))
    @JsonIgnoreProperties("workers")
    private Role role;


    public Worker() {
    }

    public Worker(Integer id, String fullname, Department department, Role role) {
        this.id = id;
        this.fullname = fullname;
        this.department = department;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }


    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(fullname, worker.fullname) && Objects.equals(department, worker.department) && Objects.equals(role, worker.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, department, role);
    }
}

