package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addUser(String name, String lastname, Integer age,
                        String username, String password, List<Integer> roleIds);

    public void deleteUser(int id);

    public void changeUser(Integer id, String name, String lastname, Integer age,
                           String username, String password, List<Integer> roleIds);

    public Optional<User> getUserById(int id);

    public List<User> getUsers();

}
