package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
    User userByUserName (String name);

    User createUser (User user);

    Iterable<User> getAllUsers ();

    void deleteUserById(Long id);

    void updateUser(User user);
}
