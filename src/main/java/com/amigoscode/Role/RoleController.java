package com.amigoscode.Role;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService,
                          RoleRepository roleRepository) {
        this.roleService = roleService;
    }

    //Read or GET Operation for all Roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllAvailableRoles();
    }

    // Insert a unique Role (U.Q)
    @PostMapping
    public ResponseEntity<String> addNewRole(
            @RequestBody Role role
    ) {
        try {
            if (role.getRole() == null || role.getRole().isEmpty()) {
                return ResponseEntity.badRequest().body("Role name must be provided");
            }
            if (role.getWorkers() == null) {
                role.setWorkers(new ArrayList<>());
            }
            roleService.insertRole(role);
            return ResponseEntity.ok("Role created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating role: " + e.getMessage());
        }
    }
}
