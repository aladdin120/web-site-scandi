package com.example.coursework.controllers;

import com.example.coursework.models.Role;
import com.example.coursework.models.User;
import com.example.coursework.models.repositories.UserRepository;
import com.example.coursework.services.EmailService;
import com.example.coursework.services.OrderService;
import com.example.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    EmailService emailService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

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
        userService.updateUserInfo(name, surname, login, form, userId);
        return "redirect:/user";
    }
}
