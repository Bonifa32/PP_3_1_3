package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUser(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam int age) {
        User user = new User(name, lastName, age);
        userService.addUser(user);
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
            @RequestParam String lastName,
            @RequestParam int age) {

        Optional<User> optional = userService.getUserById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            userService.changeUser(user);
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam int id, Model model) {
        Optional<User> optional = userService.getUserById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            model.addAttribute("user", user);
            model.addAttribute("users", userService.getUsers());
            return "editUser";
        } else {
            return "redirect:/admin/users";
        }

    }
}
