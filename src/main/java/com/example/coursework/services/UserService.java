package com.example.coursework.services;

import com.example.coursework.models.Role;
import com.example.coursework.models.User;
import com.example.coursework.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public Boolean registerUser(User newUser) {
        User userFrom = userRepository.findUserByLogin(newUser.getLogin());

        if (userFrom != null) {
            return false;
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        emailService.sendNewMessage(newUser);

        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserInfo(String name, String surname, String login, Map<String, String> form, Long userId) {
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
    }
}
