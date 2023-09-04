package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;

public interface UserService {
    User userByUserName(String name);

    User createUser(User user);

    Iterable<User> getAllUsers();

    User updateUser(User user);

    User deleteUser(User user);
}
