package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Component
public class ProgramRunner implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProgramRunner(RoleRepository roleRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeRoles();
        initializeUsers();
    }

    private void initializeRoles() {
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

    private void initializeUsers() {
        if (userRepository.findByUsername("admin") == null) {
            User user1 = new User("Albert", "Semenov", 26);
            Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
            user1.setUsername("admin");
            user1.setPassword(passwordEncoder.encode("admin"));
            user1.getRoles().add(adminRole);
            userRepository.save(user1);
        }
        if (userRepository.findByUsername("user") == null) {
            User user2 = new User("Nikita", "Ivanov", 43);
            Role userRole = roleRepository.findByRoleName("ROLE_USER");
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode("user"));
            user2.getRoles().add(userRole);
            userRepository.save(user2);

        }
    }
}
