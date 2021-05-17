package com.example.coursework.controller;

import com.example.coursework.email.EmailService;
import com.example.coursework.entities.Role;
import com.example.coursework.entities.User;
import com.example.coursework.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @GetMapping("/user")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "root";
    }

    @GetMapping("/user/{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping("/user")
    public String userSave(@RequestParam String name, @RequestParam String surname, @RequestParam String login,
                           @RequestParam Map<String, String> form, @RequestParam Long userId) {
        User user = userRepository.findUserById(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
        emailService.sendUpdateMessage(user);

        return "redirect:/user";
    }
}
