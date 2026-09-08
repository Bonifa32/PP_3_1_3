package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.annotation.PostConstruct;

@Component
public class RoleInitializier {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleInitializier(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
    if (roleRepository.findByRoleName("ROLE_USER") == null) {
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        roleRepository.save(userRole);
    }

    if (roleRepository.findByRoleName(("ROLE_ADMIN")) == null) {
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        roleRepository.save(adminRole);
    }
    }
}
