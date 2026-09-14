package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService userService;
    RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUser(Model model, @AuthenticationPrincipal User currentUser) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", currentUser);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String lastName,
                          @RequestParam int age,
                          @RequestParam List<Integer> roleIds,
                          @RequestParam String username,
                          @RequestParam String password) {
        userService.addUser(name, lastName, age, username, password, roleIds);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/update")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam int age,
            @RequestParam List<Integer> roleIds,
            @RequestParam String username,
            @RequestParam String password) {

        userService.changeUser(id, name, lastname, age, username, password,
                roleIds);

        return "redirect:/admin/users";
    }
}
