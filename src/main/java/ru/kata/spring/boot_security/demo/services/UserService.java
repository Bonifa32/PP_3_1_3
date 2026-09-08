package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addUser(User user);

    public void deleteUser(int id);

    public void changeUser(User user);

    public Optional<User> getUserById(int id);

    public List<User> getUsers();

}
