package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
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
