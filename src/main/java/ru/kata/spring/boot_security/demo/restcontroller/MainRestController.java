package ru.kata.spring.boot_security.demo.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.UserDTO;
import ru.kata.spring.boot_security.demo.service.UserService;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class MainRestController {
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @PostMapping("/users")
    public ResponseEntity<Iterable<User>> users() {
        log.info("Request for users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(path = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> delete(@RequestBody UserDTO userDTO) {
        log.info("userDTO: {}", userDTO);
        return ResponseEntity.ok(userService.deleteUser(userDTO));
    }

    @PostMapping(path = {"/create", "/edit"})
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        log.info("userDTO: {}", userDTO);
        return ResponseEntity.ok(userService.updateUser(userDTO.toUser(encoder, roleRepository)));
    }
}
