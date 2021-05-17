package com.example.coursework.controller;

import com.example.coursework.email.EmailService;
import com.example.coursework.entities.Role;
import com.example.coursework.entities.User;
import com.example.coursework.repos.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

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
        User userFrom = userRepository.findUserByLogin(newUser.getLogin());

        if (userFrom != null) {
            error = true;
            return "redirect:registration";
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        emailService.sendNewMessage(newUser);

        return "redirect:login";
    }
}
