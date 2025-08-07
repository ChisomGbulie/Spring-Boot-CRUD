package com.amigoscode.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    //<Table name, P-K>
    Optional<Role> findByRole(String role);

}