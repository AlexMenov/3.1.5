package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Service
public class UserServiceIml implements UserService, UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    public User userByUserName(String name) {
        return userRepo.findUserByUsername(name);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepo.findById(user.getId());
        createUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(userRepo.findUserByUsername(username));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles(user.get().getRoles()
                        .stream()
                        .map(Role::getRole)
                        .toArray(String[]::new))
                .build();
    }
}
