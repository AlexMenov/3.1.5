package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService, UserDetailsService {
    private final UserRepository userRepo;

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
    public User deleteUser(UserDTO userDTO) {
        Optional<User> user = userRepo.findById(userDTO.getId());
        if (user.isPresent()) {
            user.get().setRoles(new ArrayList<>());
            userRepo.deleteById(userDTO.getId());
        }
        return user.orElseGet(user::orElseThrow);
    }

    @Override
    public User updateUser(User user) {
        return createUser(user);
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
