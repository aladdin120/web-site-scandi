package com.example.coursework.controllers;

import com.example.coursework.services.EmailService;
import com.example.coursework.models.Role;
import com.example.coursework.models.User;
import com.example.coursework.models.repositories.UserRepository;
import com.example.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping
public class SecurityController {
    @Autowired
    UserService userService;

    private boolean error = false;

    @GetMapping("/registration")
    public String registration(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("error", error);

        return "registration";
    }

    @PostMapping("/registration")
    public String reg(@ModelAttribute("newUser") User newUser) {
        boolean flag = userService.registerUser(newUser);

        if (!flag) {
            error = true;
            return "redirect:registration";
        }

        return "redirect:login";
    }
}
