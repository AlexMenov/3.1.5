package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Slf4j
public class UserFrame {
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 3)
    private String username;
    private String lastName;
    @Min(0)
    private Integer age;
    @NotNull
    private String email;
    @NotNull
    @Min(8)
    private String password;
    @NotNull
    private String[] roles;

    public User toUser(PasswordEncoder encoder, RoleRepository roleRepository) {
        return new User(
                id,
                username,
                lastName,
                age,
                email,
                encoder.encode(getPassword()),
                toRoles(roleRepository.findAll()));
    }

    private Collection<Role> toRoles(Iterable<Role> allRoles) {
        return StreamSupport.stream(allRoles.spliterator(), false)
                .filter(role -> Arrays.asList(roles).contains(role.getRole()))
                .collect(Collectors.toList());
    }
}
