package com.amigoscode.Role;


import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }

    //List Available or Empty Roles
    public List<Role> getAllAvailableRoles() {
        return roleRepository.findAll();
    }

    //Insert a unique Role (U.Q)
    public void insertRole(
            Role role
    ) {
        if (role.getRole() == null || role.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role name must be provided");
        }
        // Check if role already exists
        if (roleRepository.findByRole(role.getRole()).isPresent()) {
            throw new IllegalArgumentException("Role '" + role.getRole() + "' already exists");
        }
        roleRepository.save(role);
    }
}

