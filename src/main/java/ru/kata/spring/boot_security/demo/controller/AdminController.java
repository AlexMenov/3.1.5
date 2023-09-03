package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Slf4j
@Controller
@RequestMapping(path = "/")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        return userService.userByUserName(authUser.getUsername());
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        return authUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    }

    @GetMapping(path = {"/user", "/admin"})
    public String starter() {
        return "main";
    }
}
